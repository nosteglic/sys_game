package com.yjkj.servlet;

import com.yjkj.beans.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import com.yjkj.utils.ServiceConstants;

public class GameServlet extends HttpServlet implements ServiceConstants{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        System.out.println("method:"+method);
        if(method!=null){
            if(method.equals("manager_user")){          //管理员查看用户信息表
                toManagerUserList(req,resp);
            }else if(method.equals("deleteUserById")){
                toDeleteUser(req,resp);
            }else if(method.equals("deleteUserBatch")){
                toDeleteUserBatch(req,resp);
            } else if(method.equals("menuAdmin")){    //管理员查看游戏表     √
                toManagerGameList(req,resp);
            }
            else if(method.equals("loginAdmin")){      //管理员登录          √
                toLoginM(req, resp);
            }else if(method.equals("exit")){            //退出登录          √
                toExit(req,resp);
            }else if (method.equals("ShowPutList")) {      //显示上架申请表    √
                toShowPutList(req, resp);
            } else if (method.equals("PassPutList")) {    //通过上架申请表
                toPassPutList(req, resp);
            } else if (method.equals("NoPassPutList")) {  //未通过上架申请表
                toNoPassPutList(req, resp);
            } else if (method.equals("ShowTakeList")) {   //显示下架申请表     √
                toShowTakeList(req, resp);
            } else if (method.equals("PassTakeList")) {   //通过下架申请表
                toPassTakeList(req, resp);
            } else if (method.equals("NoPassTakeList")) { //未通过下架申请表
                toNoPassTakeList(req, resp);
            } else if (method.equals("ShowChangeList")) { //显示修改申请表     √
                toShowChangeList(req, resp);
            } else if (method.equals("PassChangeList")) { //通过修改申请表     √
                toPassChangeList(req, resp);
            } else if (method.equals("NoPassChangeList")) { //未通过修改申请表
                toNoPassChangeList(req, resp);
            }else if(method.equals("deleteGameBatch")){          //批量删除游戏           √
                toDeleteGame(req,resp);
            }else if(method.equals("searchByGameId")){         //根据游戏id查询游戏         √
                toSearchByGameId(req,resp);
            }else if(method.equals("searchByGameName")){       //根据游戏名称查询游戏     √
                toSearchByGameName(req,resp);
            }else if(method.equals("searchByProducerName")){    //根据开发者名称查询游戏   √
                toSearchByProducerName(req,resp);
            }
        }else{
            System.out.println("method为空！");
        }
    }

    /**
     * 批量删除用户
     * @param req
     * @param resp
     */
    public void toDeleteUserBatch(HttpServletRequest req, HttpServletResponse resp) {
        String ids = req.getParameter("ids");
        System.out.println("需要批量删除的用户的ids:"+ids);
        Connection connection=null;
        Statement statement=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "delete from tb_user where u_id in("+ids+")";
            String sql2 = "delete from tb_shopping where u_id in("+ids+")";

            int num = statement.executeUpdate(sql);
            int i = statement.executeUpdate(sql2);

            if(num>0){
                resp.getWriter().print("true");
            }else{

                resp.getWriter().print("false");
            }


        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 根据用户id删除用户
     * @param req
     * @param resp
     */
    public void toDeleteUser(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        System.out.println("注销的用户的id："+id);
        Connection connection=null;
        Statement statement=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "delete from tb_user where u_id ='"+id+"' ";
            String sql2 = "delete from tb_shopping where u_id ='"+id+"'";

            int num = statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            if(num==1){
                resp.getWriter().print("true");
            }else{

                resp.getWriter().print("false");
            }


        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    /**
     * 显示上架申请表
     * @param request
     * @param response
     */
    /**
     * 显示上架申请表
     */
    public void toShowPutList(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tb_put where put_status = 0");

            ArrayList<Put> putList = new ArrayList<>();

            if (resultSet != null) {
                while (resultSet.next()) {
                    int put_id = resultSet.getInt("put_id");
                    Date put_time = resultSet.getDate("put_time");
                    String g_name = resultSet.getString("g_name");
                    String g_type = resultSet.getString("g_type");
                    float g_price = resultSet.getFloat("g_price");
                    float g_discount = resultSet.getFloat("g_discount");
                    String g_introduction = resultSet.getString("g_introduction");
                    int g_age = resultSet.getInt("g_age");
                    int u_id = resultSet.getInt("u_id");


                    Put put = new Put(put_id, put_time, g_name, g_type,g_price, g_discount, g_introduction, g_age, 0,u_id);

                    putList.add(put);
                    System.out.println(put);
                }
            }

            HttpSession session = request.getSession();

            //把数据存放到作用域
            session.setAttribute("putList", putList);
//            //重定向
            response.sendRedirect("http://localhost:8080/jsp/showOnline.jsp");

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //关闭数据库资源
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }


    /**
     * 通过某个游戏的上架申请
     * 改当前上架申请的处理状态+增加游戏
     * 应该加上事务管理，但是好麻烦【？有空再说
     */
    public void toPassPutList(HttpServletRequest request, HttpServletResponse response) {

        //传入上架申请表id
        String put_id = request.getParameter("put_id");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSet2=null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql = "update tb_put set put_status = 1 where put_id = '"+put_id+"'";
            int num = statement.executeUpdate(sql);
            if (num == 1){
                resultSet = statement.executeQuery("select * from tb_put where put_id = "+put_id+"");
                if (resultSet.next()) {
                    String g_name=resultSet.getString("g_name");
                    String g_type=resultSet.getString("g_type");
                    float g_price=resultSet.getFloat("g_price");
                    float g_discount=resultSet.getFloat("g_discount");
                    String g_introduction=resultSet.getString("g_introduction");
                    int g_age=resultSet.getInt("g_age");
                    int u_id=resultSet.getInt("u_id");
                    resultSet2 = statement.executeQuery("select u_username from tb_user where u_id='" + resultSet.getInt("u_id") + "'");
                    if (resultSet2.next()) {
                        String u_name = resultSet2.getString("u_username");
                        sql = "insert tb_game(g_name, g_type, g_price,g_discount,g_introduction,g_time,g_age,u_id,u_name) values " + "('" + g_name + "','" + g_type + "','" + g_price + "','" + g_discount + "','" + g_introduction + "','" + new java.sql.Date(new Date().getTime()) + "','" + g_age + "','" +u_id + "','" + u_name + "')";
                    }
                }

                num = statement.executeUpdate(sql);
                if (num == 1) {
                    response.getWriter().print("true");
                }else {
                    response.getWriter().print("false");
                }

            }else {
                response.getWriter().print("false");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭数据库资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    /**
     * 上架审核不通过
     * 仅修改状态
     */
    public void toNoPassPutList(HttpServletRequest request, HttpServletResponse response) {

        //传入申请表id
        String put_id = request.getParameter("put_id");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();
            //定义sql
            String sql = "update tb_put set put_status = -1 where put_id = "+put_id+"";
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                response.getWriter().print("true");
            }else {
                response.getWriter().print("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭数据库资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }


    /**
     * 显示下架申请表
     */
    public void toShowTakeList(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tb_take where take_status = 0");

            ArrayList<Take> takeList = new ArrayList<>();


            if (resultSet != null) {

                //遍历结果集
                while (resultSet.next()) {

                    int take_id = resultSet.getInt("take_id");
                    Date take_time = resultSet.getDate("take_time");
                    int g_id = resultSet.getInt("g_id");
                    String g_name = resultSet.getString("g_name");
                    String take_reason = resultSet.getString("take_reason");
                    int u_id = resultSet.getInt("u_id");

                    Take take = new Take(take_id, take_time,g_id, g_name,take_reason,0,u_id);

                    takeList.add(take);

                    System.out.println(take);
                }
            }

            HttpSession session = request.getSession();

            //把数据存放到作用域
            session.setAttribute("takeList", takeList);
//            //重定向
            response.sendRedirect("http://localhost:8080/jsp/showOffline.jsp");

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //关闭数据库资源
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }



    /**
     * 通过某个游戏的下架申请
     * 改当前下架申请的处理状态+删游戏
     */
    public void toPassTakeList(HttpServletRequest request, HttpServletResponse response) {

        //传入下架申请表id
        String take_id = request.getParameter("take_id");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql = "update tb_take set take_status = 1 where take_id = '"+take_id+"'";
            int num = statement.executeUpdate(sql);
            if (num == 1){
                resultSet = statement.executeQuery("select * from tb_take where take_id = '"+take_id+"'");

                if (resultSet.next()) {
                    num = statement.executeUpdate("delete from tb_game where g_id ='"+resultSet.getInt("g_id")+"' ");
                }

                num = statement.executeUpdate(sql);
                if (num == 1) {
                    response.getWriter().print("true");
                }else {
                    response.getWriter().print("false");
                }

            }else {
                response.getWriter().print("false");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭数据库资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    /**
     * 下架审核不通过
     * 仅修改状态
     */
    public void toNoPassTakeList(HttpServletRequest request, HttpServletResponse response) {

        //传入申请表id
        String take_id = request.getParameter("take_id");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();
            //定义sql
            String sql = "update tb_take set take_status = -1 where take_id = '"+take_id+"'";
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                response.getWriter().print("true");
            }else {
                response.getWriter().print("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭数据库资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }


    /**
     * 显示修改申请表
     */
    public void toShowChangeList(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tb_change where change_status = 0");

            ArrayList<Change> changeList = new ArrayList<>();


            if (resultSet != null) {

                //遍历结果集
                while (resultSet.next()) {

                    int change_id = resultSet.getInt("change_id");
                    Date change_time = resultSet.getDate("change_time");
                    int g_id = resultSet.getInt("g_id");
                    String g_name = resultSet.getString("g_name");
                    String g_type = resultSet.getString("g_type");
                    float g_price = resultSet.getFloat("g_price");
                    float g_discount = resultSet.getFloat("g_discount");
                    String g_introduction = resultSet.getString("g_introduction");
                    Date g_time = resultSet.getDate("g_time");
                    int g_age = resultSet.getInt("g_age");
                    int u_id = resultSet.getInt("u_id");

                    Change change = new Change(change_id, change_time,g_id, g_name,g_type, g_price, g_discount, g_introduction,g_time, g_age,0,u_id);

                    changeList.add(change);
                }
            }

            HttpSession session = request.getSession();

            //把数据存放到作用域
            session.setAttribute("changeList", changeList);
            //重定向
            response.sendRedirect("http://localhost:8080/jsp/showModify.jsp");

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //关闭数据库资源
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }



    /**
     * 通过某个游戏的修改申请
     * 改当前申请的处理状态+改游戏
     */
    public void toPassChangeList(HttpServletRequest request, HttpServletResponse response) {

        String change_id = request.getParameter("change_id");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql = "update tb_change set change_status = 1 where change_id = '"+change_id+"'";
            int num = statement.executeUpdate(sql);
            if (num == 1){
                resultSet = statement.executeQuery("select * from tb_change where change_id = '"+change_id+"'");

                if (resultSet.next()) {
                    sql="update tb_game set g_name='"+resultSet.getString("g_name")+"', g_type ='"+resultSet.getString("g_type")+"', g_price ='"+resultSet.getFloat("g_price")+"', g_discount ='"+resultSet.getFloat("g_discount")+"', g_introduction ='"+resultSet.getString("g_introduction") +"',g_time ='"+resultSet.getDate("g_time")+"',g_age ='"+resultSet.getInt("g_age")+ "'where g_id='"+resultSet.getInt("g_id")+"'";

                }

                num = statement.executeUpdate(sql);
                if (num == 1) {
                    response.getWriter().print("true");
                }else {
                    response.getWriter().print("false");
                }

            }else {
                response.getWriter().print("false");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭数据库资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }
    /**
     * 修改审核不通过
     * 仅修改状态
     */
    public void toNoPassChangeList(HttpServletRequest request, HttpServletResponse response) {

        //传入申请表id
        String change_id = request.getParameter("change_id");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();
            //定义sql
            String sql = "update tb_change set change_status = -1 where change_id = '"+change_id+"'";
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                response.getWriter().print("true");
            }else {
                response.getWriter().print("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭数据库资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }
    public void toManagerGameList(HttpServletRequest req, HttpServletResponse resp) {

        int current=1;
        if(req.getParameter("current")!=null){
            current=Integer.parseInt(req.getParameter("current"));
        }

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        Page page=new Page();

        System.out.println("访问游戏列表显示方法成功！");
        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select count(g_id) from tb_game ");
            if (resultSet != null) {
                while (resultSet.next()) {
                    page.setRows(resultSet.getInt(1));
                }
            }
            //总页数
            page.setTotal(page.getRows(), page.getLimit());
            page.setCurrent(current);
            String sql = "select * from tb_game limit "+page.getOffset()+","+page.getLimit()+"";
            resultSet = statement.executeQuery(sql);
            ArrayList<Game> games = new ArrayList<>();

            if(resultSet!=null){

                while (resultSet.next()){

                    int id = resultSet.getInt("g_id");
                    String name = resultSet.getString("g_name");
                    String type = resultSet.getString("g_type");
                    float price = resultSet.getFloat("g_price");
                    float discount = resultSet.getFloat("g_discount");
                    String introduction = resultSet.getString("g_introduction");
                    String time = resultSet.getString("g_time");
                    int age = resultSet.getInt("g_age");
                    int p_id = resultSet.getInt("u_id");
                    String p_name = resultSet.getString("u_name");

                    Game game = new Game();

                    game.setId(id);
                    game.setName(name);
                    game.setType(type);
                    game.setPrice(price);
                    game.setDiscount(discount);
                    game.setIntroduction(introduction);
                    game.setTime(time);
                    game.setAge(age);
                    game.setP_id(p_id);
                    game.setP_name(p_name);

                    games.add(game);

                    System.out.println(game);
                }

                HttpSession session = req.getSession();

                session.setAttribute("games",games);
                session.setAttribute("page", page);
            }



        } catch ( Exception e) {
            e.printStackTrace();
        }finally {

            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        try {
            resp.sendRedirect("http://localhost:8080/jsp/menuAdmin.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toManagerUserList(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "select * from tb_user ";

            resultSet = statement.executeQuery(sql);

            ArrayList<User> users = new ArrayList<>();

            if(resultSet!=null){

                while (resultSet.next()){

                    int id = resultSet.getInt("u_id");

                    String username = resultSet.getString("u_username");

                    String password = resultSet.getString("u_password");

                    int type = resultSet.getInt("u_type");

                    String id_number = resultSet.getString("u_ID_number");

                    String phone = resultSet.getString("u_phone");

                    String address = resultSet.getString("u_address");

                    User user = new User();

                    user.setId(id);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setType(type);
                    user.setID_number(id_number);
                    user.setPhone(phone);
                    user.setAddress(address);

                    users.add(user);

                    System.out.println(user);
                }

                HttpSession session = req.getSession();

                session.setAttribute("users",users);
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }finally {

            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        try {
            resp.sendRedirect("http://localhost:8080/jsp/showUsers.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员登录的方法
     * @param req
     * @param resp
     */
    public void toLoginM(HttpServletRequest req, HttpServletResponse resp) {
        int flag=0;

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"    "+password);

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "select * from tb_manager";

            resultSet = statement.executeQuery(sql);

            if(resultSet!=null){

                while (resultSet.next()){

                    int m_id = resultSet.getInt("m_id");
                    String username1 = resultSet.getString("m_username");

                    String password1 = resultSet.getString("m_password");


                    System.out.println(username1+"   "+password1);


                    if(username.equals(username1) && password.equals(password1)){

                        flag=1;

                        User loginUser = new User();
                        loginUser.setId(m_id);
                        loginUser.setUsername(username);
                        loginUser.setPassword(password);

                        HttpSession session = req.getSession();

                        //退出用户登录
                        session.removeAttribute("loginUser");
                        session.removeAttribute("producerFlag");

                        session.setAttribute("loginAdmin",loginUser);
                        System.out.println("当前登录管理员："+loginUser);

                        System.out.println("管理员登录成功");
                        break;
                    }

                }
            }

            if(flag==1){
                resp.getWriter().print("true");
            }else{
                resp.getWriter().print("false");
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 管理员退出登录的方法
     * @param req
     * @param resp
     */
    public void toExit(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("访问管理员退出登录方法成功！");
        HttpSession session = req.getSession();
        session.removeAttribute("loginAdmin");
        try {
            resp.sendRedirect("http://localhost:8080/user?method=showGameList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量删除游戏的方法
     * @param req
     * @param resp
     */
    public void toDeleteGame(HttpServletRequest req, HttpServletResponse resp) {

        String ids = req.getParameter("ids");
        System.out.println("访问toDeleteGame方法成功！");
        Connection connection=null;
        Statement statement=null;
        System.out.println(ids);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();

            int num = statement.executeUpdate("delete from tb_game where g_id in("+ids+")");
            if(num>0){

                resp.getWriter().print("true");
            }else{

                resp.getWriter().print("false");
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }finally {

            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 根据开发者查询游戏的方法
     * @param req
     * @param resp
     */
    public void toSearchByProducerName(HttpServletRequest req, HttpServletResponse resp) {
        //传入当前页码
        int current=1;
        if(req.getParameter("current")!=null){
            current=Integer.parseInt(req.getParameter("current"));
        }

        String queryByProducerName = req.getParameter("queryByProducerName");

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        Page page=new Page();

        System.out.println("模糊的游戏商名：" + queryByProducerName);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("select count(g_id) from tb_game where u_name like '%"+queryByProducerName+"%'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    page.setRows(resultSet.getInt(1));
                }
            }
            //总页数
            page.setTotal(page.getRows(), page.getLimit());
            page.setCurrent(current);

            String sql=" select * from tb_game where u_name like '%"+queryByProducerName+"%'limit "+page.getOffset()+","+page.getLimit()+"";


            resultSet = statement.executeQuery(sql);

            ArrayList<Game> SearchByProducerName = new ArrayList<>();

            if(resultSet!=null){
                while (resultSet.next()){
                    int g_id = resultSet.getInt("g_id");
                    String g_name = resultSet.getString("g_name");
                    String g_type = resultSet.getString("g_type");
                    float g_price = resultSet.getFloat("g_price");
                    float g_discount = resultSet.getFloat("g_discount");
                    String g_introduction = resultSet.getString("g_introduction");
                    String g_time = resultSet.getString("g_time");
                    int g_age = resultSet.getInt("g_age");
                    int u_id = resultSet.getInt("u_id");
                    String u_name = resultSet.getString("u_name");

                    Game game = new Game();
                    game.setId(g_id);
                    game.setName(g_name);
                    game.setType(g_type);
                    game.setPrice(g_price);
                    game.setDiscount(g_discount);
                    game.setIntroduction(g_introduction);
                    game.setTime(g_time);
                    game.setAge(g_age);
                    game.setP_id(u_id);
                    game.setP_name(u_name);

                    System.out.println("中间结果："+game);

                    SearchByProducerName.add(game);
                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("games",SearchByProducerName);
                //总页数
                session.setAttribute("page", page);
                System.out.println("根据游戏商名模糊查询游戏结果如下：");
                System.out.println(SearchByProducerName);
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 根据游戏名称查询游戏的方法
     * @param req
     * @param resp
     */
    public void toSearchByGameName(HttpServletRequest req, HttpServletResponse resp) {
        //传入当前页码
        int current=1;
        if(req.getParameter("current")!=null){
            current=Integer.parseInt(req.getParameter("current"));
        }

        String queryByGameName = req.getParameter("queryByGameName");

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        Page page=new Page();

        System.out.println("模糊的游戏名：" + queryByGameName);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("select count(g_id) from tb_game where g_name like '%"+queryByGameName+"%'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    page.setRows(resultSet.getInt(1));
                }
            }
            //总页数
            page.setTotal(page.getRows(), page.getLimit());
            page.setCurrent(current);

            String sql = "select * from tb_game where g_name like '%"+queryByGameName+"%' limit "+page.getOffset()+","+page.getLimit()+"";

            resultSet = statement.executeQuery(sql);

            ArrayList<Game> SearchByGameName = new ArrayList<>();

            if(resultSet!=null){
                while (resultSet.next()){
                    int g_id = resultSet.getInt("g_id");
                    String g_name = resultSet.getString("g_name");
                    String g_type = resultSet.getString("g_type");
                    float g_price = resultSet.getFloat("g_price");
                    float g_discount = resultSet.getFloat("g_discount");
                    String g_introduction = resultSet.getString("g_introduction");
                    String g_time = resultSet.getString("g_time");
                    int g_age = resultSet.getInt("g_age");
                    int u_id = resultSet.getInt("u_id");
                    String u_name = resultSet.getString("u_name");

                    Game game = new Game();
                    game.setId(g_id);
                    game.setName(g_name);
                    game.setType(g_type);
                    game.setPrice(g_price);
                    game.setDiscount(g_discount);
                    game.setIntroduction(g_introduction);
                    game.setTime(g_time);
                    game.setAge(g_age);
                    game.setP_id(u_id);
                    game.setP_name(u_name);

                    System.out.println("中间结果："+game);

                    SearchByGameName.add(game);
                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("games",SearchByGameName);
                session.setAttribute("page", page);

                System.out.println("根据游戏名模糊查询游戏结果如下：");
                System.out.println(SearchByGameName);
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 根据游戏id查询游戏的方法
     * @param req
     * @param resp
     */
    public void toSearchByGameId(HttpServletRequest req, HttpServletResponse resp) {
        String queryByGameId = req.getParameter("queryByGameId");

        System.out.println("查询的游戏的id："+queryByGameId);

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();



            String sql=" select * from tb_game where g_id ='"+queryByGameId+"'";

            resultSet = statement.executeQuery(sql);

            ArrayList<Game> SearchGamesById = new ArrayList<>();

            if(resultSet!=null){
                while (resultSet.next()){
                    int g_id1 = resultSet.getInt("g_id");
                    String g_name = resultSet.getString("g_name");
                    String g_type = resultSet.getString("g_type");
                    float g_price = resultSet.getFloat("g_price");
                    float g_discount = resultSet.getFloat("g_discount");
                    String g_introduction = resultSet.getString("g_introduction");
                    String g_time = resultSet.getString("g_time");
                    int g_age = resultSet.getInt("g_age");
                    int u_id = resultSet.getInt("u_id");
                    String u_name = resultSet.getString("u_name");

                    Game game = new Game();
                    game.setId(g_id1);
                    game.setName(g_name);
                    game.setType(g_type);
                    game.setPrice(g_price);
                    game.setDiscount(g_discount);
                    game.setIntroduction(g_introduction);
                    game.setTime(g_time);
                    game.setAge(g_age);
                    game.setP_id(u_id);
                    game.setP_name(u_name);

                    SearchGamesById.add(game);
                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("games", SearchGamesById);
                System.out.println(SearchGamesById);
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

