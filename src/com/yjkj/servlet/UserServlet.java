package com.yjkj.servlet;

import com.yjkj.beans.*;
import com.yjkj.utils.ServiceConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserServlet extends HttpServlet implements ServiceConstants {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println("method:"+method);
        if(method!=null) {
            if (method.equals("loginUser")) {               //用户登录      √
                toLoginU(req, resp);
            } else if (method.equals("registerUser")) {     //用户注册      √
                toRegister(req, resp);
            }else if(method.equals("modifyPwd")){           //修改密码      √
                toModifyPwd(req,resp);
            }else if(method.equals("exit")){                //用户退出登录    √
                toExit(req,resp);
            }else if(method.equals("logout")){              //用户注销  √
                toLogOut(req,resp);
            } else if(method.equals("showGameList")){       //游戏列表显示    √
                toShowGameList(req,resp);
            } else if(method.equals("registerProducer")){   //申请成为开发者按钮，跳转更新个人信息界面
                toUpdateUser(req,resp);
            }else if(method.equals("shoppingCart")){        //查看购物车数据   √
                toShoppingList(req,resp);
            }else if(method.equals("addShopping")){         //加入购物车     √
                toAddShopping(req,resp);
            }else if(method.equals("deleteShopping")){      //移出购物车     √
                toDeleteShopping(req,resp);
            }else if(method.equals("searchLikeName")){      //用户按游戏名模糊搜索游戏表     √
                toSearch(req, resp);
            }else if(method.equals("searchByType")){        //用户按类型名模糊搜索游戏表     √
                toSearchByType(req,resp);
            }else if(method.equals("showGameDetailById")){  //根据游戏id查看该游戏的详情    √
                toShowGameDetailById(req,resp);
            }else if (method.equals("toInsertComment")) {  //用户对某一款游戏发表评论   √
                toInsertComment(req, resp);
            }else if (method.equals("toSelectCommentByGameId")) {  //根据游戏id查询游戏评论   √
                toSelectCommentByGameId(req, resp);
            }else if(method.equals("selectOnlineGame")){        //查询上架游戏    √
                toSelectOnlineGame(req,resp);
            }else if (method.equals("toPutGame")) {       //申请上架    √
                toPutGame(req, resp);
            }else if (method.equals("toTakeGame")) {       //申请下架   √
                toTakeGame(req, resp);
            } else if (method.equals("toChangeGame")) {       //申请修改    √
                toChangeGame(req, resp);
            }else if(method.equals("chsOneMyGame")){
                toGetChsOnlineGame(req,resp);
            }

        }
        else {
            System.out.println("method为空！");
        }
    }

    /**
     * 显示游戏列表
     * @param req
     * @param resp
     */
    public void toShowGameList(HttpServletRequest req, HttpServletResponse resp) {

        //传入当前页码
        int current=1;
        if(req.getParameter("current")!=null){
            current=Integer.parseInt(req.getParameter("current"));
        }

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        Page page=new Page();

        System.out.println("访问游戏列表显示方法（用户端）成功！");
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
                //总页数
                session.setAttribute("page", page);
                resp.sendRedirect("http://localhost:8080/jsp/menuUser.jsp");
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
     * 修改密码
     * @param req
     * @param resp
     */
    public void toModifyPwd(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("修改密码方法访问成功");
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        System.out.println("前端传入的用户ID和密码"+userId+" "+password);

        Connection connection=null;
        Statement statement=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "update tb_user set u_password='"+password+"' where u_id='"+userId+"'";

            int num = statement.executeUpdate(sql);
            if(num==1){
                System.out.println("用户修改密码成功！");
                resp.getWriter().print("true");
            }else{
                System.out.println("用户修改密码出错");
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
        HttpSession session = req.getSession();
        session.removeAttribute("loginUser");
//        session.removeAttribute("producerFlag");

    }

    /**
     * 用户登录
     * @param req
     * @param resp
     */
    public void toLoginU(HttpServletRequest req, HttpServletResponse resp) {

        int flag=0;
        System.out.println("访问toLoginU方法成功！");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("前端传入的用户名和密码"+username+"    "+password);

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "select * from tb_user";

            resultSet = statement.executeQuery(sql);

            if(resultSet!=null){

                while (resultSet.next()){

                    int id = resultSet.getInt("u_id");

                    String username1 = resultSet.getString("u_username");

                    String password1 = resultSet.getString("u_password");
                    System.out.println("数据库中的用户名和密码"+username1+"   "+password1);
                    if(username.equals(username1) && password.equals(password1)){
                        int u_type = resultSet.getInt("u_type");
                        String u_id_number = resultSet.getString("u_ID_number");
                        String u_phone = resultSet.getString("u_phone");
                        String u_address = resultSet.getString("u_address");

                        User loginUser = new User();
                        loginUser.setId(id);
                        loginUser.setUsername(username);
                        loginUser.setPassword(password);
                        loginUser.setType(u_type);
                        loginUser.setID_number(u_id_number);
                        loginUser.setPhone(u_phone);
                        loginUser.setAddress(u_address);
                        HttpSession session = req.getSession();

                        session.setAttribute("loginUser",loginUser);

                        System.out.println("当前登录用户："+loginUser);

                        flag=1;
                        System.out.println("用户登录成功");

                        if(u_type==1){
                            System.out.println("登录的身份：用户");
                        }else{
//                            session.setAttribute("producerFlag",1);
                            System.out.println("登录的身份：开发者");
                        }
                        break;
                    }
                }
            }else{
                System.out.println("当前用户表为空");
            }
            if(flag==1){
                resp.getWriter().print(0);
            }else{
                resp.getWriter().print(1);
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
     * 用户注册
     * @param req
     * @param resp
     */
    public void toRegister(HttpServletRequest req, HttpServletResponse resp) {

        int flag=1;
        System.out.println("注册方法访问成功");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("前端传入的用户名和密码"+username+" "+password);

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "select * from tb_user";

            resultSet = statement.executeQuery(sql);

            if(resultSet!=null){

                while (resultSet.next()){

                    String username1 = resultSet.getString("u_username");

                    if(username.equals(username1)){
                        flag=0;
                        System.out.println("失败，用户名已存在，请重新输入！！");
                        break;
                    }

                }
            }

            if(flag==1){
                //用户名数据库中无重复，可以成功注册
                //普通用户为1，个人开发商为2，游戏开发商为3
                int i = statement.executeUpdate("insert into tb_user (u_username,u_password,u_type) values ('" + username + "','" + password + "','1')");

                System.out.println(i);

                if(i==1)
                    resp.getWriter().print("0");
                else
                    resp.getWriter().print("2");

            }else{
                //提示用户用户名已存在，注册失败
                resp.getWriter().print("1");
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
     * 在登录界面后台将用户名的ID传入session中，然后显示在list界面，点击发布游戏后通过session传入id到后台
     * @param req
     * @param resp
     */
    public void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("userId");
        String type = req.getParameter("userType");
        String id_number = req.getParameter("userCard");
        String phone = req.getParameter("userPhone");
        String address = req.getParameter("userAddress");
        System.out.println("访问成为注册者方法成功！"+id+","+type+","+id_number+","+phone+","+address);
        Connection connection=null;
        Statement statement=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            //2为个人开发商，3为公司
            String sql="update  tb_user set u_type = '"+type+"',u_ID_number='"+id_number+"', u_phone ='"+phone+"', u_address ='"+address+"' where u_id='"+id+"'";

            int num = statement.executeUpdate(sql);

            if(num==1){
                HttpSession session = req.getSession();
                Object loginUser = session.getAttribute("loginUser");
                User loginUser1 = (User) loginUser;
                System.out.println("测试成为开发者的loginUser："+loginUser);
                loginUser1.setType(Integer.parseInt(type));
                loginUser1.setID_number(id_number);
                loginUser1.setPhone(phone);
                loginUser1.setAddress(address);
                session.setAttribute("loginUser",loginUser1);
//                session.setAttribute("producerFlag",1);
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
     * 显示购物车
     * @param req
     * @param resp
     */
    public void toShoppingList(HttpServletRequest req, HttpServletResponse resp) {

        String u_id0 = req.getParameter("userId");
        System.out.println("购物车显示方法访问成功");
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        ArrayList<Shopping> shoppings = new ArrayList<>();

        try {

            Class.forName("com.mysql.jdbc.Driver");

//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();

            String sql = "select * from tb_shopping left join tb_game on tb_shopping.g_id=tb_game.g_id left join tb_user on tb_shopping.u_id=tb_user.u_id where tb_shopping.u_id='"+u_id0+"'";

            resultSet = statement.executeQuery(sql);

            if(resultSet!=null){

                while (resultSet.next()){

                    int s_id = resultSet.getInt("s_id");

                    int u_id = resultSet.getInt("u_id");//当前用户名id

                    int g_id = resultSet.getInt("g_id");

                    String u_name = resultSet.getString("u_name");//当前用户名

                    String g_name = resultSet.getString("g_name");//游戏名

                    String g_price = resultSet.getString("g_price");//游戏价格

                    Shopping shopping = new Shopping();

                    shopping.setId(s_id);
                    shopping.setC_id(u_id);
                    shopping.setG_id(g_id);
                    shopping.setUsername(u_name);//用户名
                    shopping.setG_name(g_name);
                    shopping.setG_price(g_price);

                    shoppings.add(shopping);

                    System.out.println(shopping);
                }

                HttpSession session = req.getSession();
                session.setAttribute("shoppings",shoppings);
            }
            else{
                System.out.println("当前用户购物车为空");
            }
            resp.sendRedirect("http://localhost:8080/jsp/shoppingCart.jsp");


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
     * 删除物品
     * @param req
     * @param resp
     */
    public  void toDeleteShopping(HttpServletRequest req, HttpServletResponse resp) {

        String ids = req.getParameter("ids");
        System.out.println("访问toDeleteShopping方法成功");
        Connection connection=null;
        Statement statement=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();

            int num = statement.executeUpdate("delete from tb_shopping where s_id in("+ids+")");


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
     * 添加物品
     * @param req
     * @param resp
     */
    public void toAddShopping(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("访问添加购物车函数成功！");
        int flag=1;
        int num=0;

        String userId = req.getParameter("userId");//用户id
        String gameId = req.getParameter("gameId");//游戏id

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();

            String sql0="SELECT COUNT(*) as totalcount FROM tb_shopping where u_id='"+userId+"'";
            String sql = "select * from tb_shopping";

            ResultSet resultSet1 = statement.executeQuery(sql0);
            if(resultSet1.next()) {
                num=resultSet1 .getInt("totalCount");
            }
            System.out.println("当前购物车中的物品数量："+num);
            if(num==5){
                System.out.println("购物车已满！！！");
                resp.getWriter().print(3);
//                System.out.println(2);
            }else {
                resultSet = statement.executeQuery(sql);

                if(resultSet!=null){

                    while (resultSet.next()){

                        String u_id1 = resultSet.getString("u_id");
                        String g_id1 = resultSet.getString("g_id");

                        if(userId.equals(u_id1)&&gameId.equals(g_id1)){
                            flag=0;
                            System.out.println("加入购物车失败，购物车中已存在！！");
                            break;
                        }

                    }
                }

                if(flag==1){
                    //购物车记录数据库中无重复，可以成功加入

                    int i = statement.executeUpdate("insert into tb_shopping (u_id,g_id) values ('" + userId + "','" + gameId + "')");

                    if(i==1)
                        resp.getWriter().print(1);
                    else
                        resp.getWriter().print(4);
                }else{
                    //提示用户购物车中该记录已存在，加入失败
                    resp.getWriter().print(2);
                }
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
     * 根据游戏名模糊查询
     * @param req
     * @param resp
     */
    public void toSearch(HttpServletRequest req, HttpServletResponse resp) {
        //传入当前页码
        int current=1;
        if(req.getParameter("current")!=null){
            current=Integer.parseInt(req.getParameter("current"));
        }

        String queryLikeName = req.getParameter("queryLikeName");
        System.out.println("访问根据游戏名模糊查询方法成功！");
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        Page page=new Page();
        System.out.println("模糊的游戏名：" + queryLikeName);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();


            resultSet = statement.executeQuery("select count(g_id) from tb_game where g_name like '%"+queryLikeName+"%'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    page.setRows(resultSet.getInt(1));
                }
            }
            //总页数
            page.setTotal(page.getRows(), page.getLimit());
            page.setCurrent(current);
            String sql = "select * from tb_game where g_name like '%"+queryLikeName+"%' limit "+page.getOffset()+","+page.getLimit()+"";


            resultSet = statement.executeQuery(sql);

            ArrayList<Game> searchGames = new ArrayList<>();

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

                    searchGames.add(game);
                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("games",searchGames);
                //总页数
                session.setAttribute("page", page);
                System.out.println("根据游戏名模糊查询游戏结果如下：");
                System.out.println(searchGames);
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
     * 根据游戏类型查询游戏
     * @param req
     * @param resp
     */
    public void toSearchByType(HttpServletRequest req, HttpServletResponse resp) {
        //传入当前页码
        int current=1;
        if(req.getParameter("current")!=null){
            current=Integer.parseInt(req.getParameter("current"));
        }
        System.out.println("访问根据游戏类型查询游戏方法成功！");
        String gameType = req.getParameter("gameType");
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        Page page=new Page();
        System.out.println("返回的游戏类型："+gameType);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_system??useUnicode=true&amp&characterEncoding=UTF-8", "root", "123456");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();



            resultSet = statement.executeQuery("select count(g_id) from tb_game where g_type like '%"+gameType+"%'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    page.setRows(resultSet.getInt(1));
                }
            }
            //总页数
            page.setTotal(page.getRows(), page.getLimit());
            page.setCurrent(current);
            String sql = "select * from tb_game where g_type like '%"+gameType+"%' limit "+page.getOffset()+","+page.getLimit()+"";



            resultSet = statement.executeQuery(sql);

            ArrayList<Game> searchByType = new ArrayList<>();

            if(resultSet!=null){
                while (resultSet.next()){
                    int g_id = resultSet.getInt("g_id");
                    String g_name = resultSet.getString("g_name");
                    String g_type1 = resultSet.getString("g_type");
                    float g_price = resultSet.getFloat("g_price");
                    float g_discount = resultSet.getFloat("g_discount");
                    String g_introduction = resultSet.getString("g_introduction");
                    Date g_time = resultSet.getDate("g_time");
                    int g_age = resultSet.getInt("g_age");
                    int u_id = resultSet.getInt("u_id");
                    String u_name = resultSet.getString("u_name");

                    Game game = new Game();
                    game.setId(g_id);
                    game.setName(g_name);
                    game.setType(g_type1);
                    game.setPrice(g_price);
                    game.setDiscount(g_discount);
                    game.setIntroduction(g_introduction);
                    game.setTime(g_time.toString());
                    game.setAge(g_age);
                    game.setP_id(u_id);
                    game.setP_name(u_name);

                    System.out.println(game);

                    searchByType.add(game);
                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("games",searchByType);
                //总页数
                session.setAttribute("page", page);
                System.out.println("按类型搜索到的数据：");
                System.out.println(searchByType);
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
            resp.sendRedirect("http://localhost:8080/jsp/menuUser.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toLogOut(HttpServletRequest req, HttpServletResponse resp) {


        String id = req.getParameter("id");

        Connection connection=null;
        Statement statement=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql = "delete from tb_user where u_id ='"+id+"'";
            String sql2 = "delete from tb_shopping where u_id ='"+id+"'";

            System.out.println(sql);
            System.out.println(sql2);

            int num = statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            if(num==1){
                resp.getWriter().print(0);
                HttpSession session = req.getSession();
                session.removeAttribute("loginUser");
//                session.removeAttribute("producerFlag");
            }else{
                resp.getWriter().print(1);
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

    public void toExit(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("访问用户退出登录方法成功！");
        HttpSession session = req.getSession();
        session.removeAttribute("loginUser");
//        session.removeAttribute("producerFlag");
        try {
            resp.sendRedirect("http://localhost:8080/jsp/menuUser.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*********************************以上2021.1.12**************************************************/
    /**
     * 根据游戏id查看对应的游戏的详情
     * @param req
     * @param resp
     */
    public void toShowGameDetailById(HttpServletRequest req, HttpServletResponse resp) {

        String gameId = req.getParameter("gameId");

        System.out.println("要显示详情的游戏的id："+gameId);

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        Game gameDetail = new Game();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();
            String sql=" select * from tb_game where g_id ='"+gameId+"'";

            resultSet = statement.executeQuery(sql);


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


                    gameDetail.setId(id);
                    gameDetail.setName(name);
                    gameDetail.setType(type);
                    gameDetail.setPrice(price);
                    gameDetail.setDiscount(discount);
                    gameDetail.setIntroduction(introduction);
                    gameDetail.setTime(time);
                    gameDetail.setAge(age);
                    gameDetail.setP_id(p_id);
                    gameDetail.setP_name(p_name);

                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("gameDetail",gameDetail);
                System.out.println(gameDetail);
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
            resp.sendRedirect("http://localhost:8080/user?method=toSelectCommentByGameId&gameId="+gameId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户对某一款游戏发表评论
     * 传入用户id（c_id） 游戏id （g_id）要评论的内容（comment_content）
     */
    public void toInsertComment(HttpServletRequest req, HttpServletResponse resp) {
        //传入用户id 游戏id 要评论的内容
        String c_id = req.getParameter("userId");
        String g_id = req.getParameter("gameId");
        String comment_content = req.getParameter("comment");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql =  "insert tb_comment(c_id, g_id, comment_content,comment_time) values " + "('" + c_id + "','" + g_id + "','" + comment_content + "','" + new Date(new java.util.Date().getTime())+ "')";

            int num = statement.executeUpdate(sql);
            if (num == 1) {
                resp.getWriter().print("true");
            } else {
                resp.getWriter().print("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
     * 根据游戏id查询游戏评论
     * 传入g_id
     */
    public void toSelectCommentByGameId(HttpServletRequest req, HttpServletResponse resp){
        //传入游戏id
        String g_id = req.getParameter("gameId");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tb_comment as a,tb_game as b,tb_user as c where a.g_id = b.g_id and a.c_id = c.u_id and a.g_id= "+g_id+"");

            ArrayList<Comment> commentList = new ArrayList<>();

            if (resultSet != null) {
                while (resultSet.next()) {
                    //评论id
                    int comment_id = resultSet.getInt("comment_id");
                    //用户id
                    int c_id = resultSet.getInt("c_id");
                    //用户名
                    String u_username=resultSet.getString("u_username");
                    //游戏名
                    String g_name = resultSet.getString("g_name");
                    //游戏类型
                    String g_type = resultSet.getString("g_type");
                    //评论内容
                    String comment_content = resultSet.getString("comment_content");
                    //评论时间
                    java.util.Date comment_time = resultSet.getDate("comment_time");

                    Comment comment=new Comment(comment_id,c_id,u_username,Integer.parseInt(g_id),g_name,g_type,comment_content,comment_time);

                    commentList.add(comment);
                }
            }
            HttpSession session = req.getSession();

            //把数据存放到作用域
            session.setAttribute("commentLists", commentList);

            System.out.println(commentList);

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
        try {
            resp.sendRedirect("http://localhost:8080/jsp/gameDetails.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据开发者id查询其上架的游戏的方法
     * @param req
     * @param resp
     */
    public void toSelectOnlineGame(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("userId");
        System.out.println("当前登录用户id："+id);
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            String sql = "select * from tb_game where u_id='"+id+"'";
            resultSet = statement.executeQuery(sql);
            ArrayList<Game> onlineGames = new ArrayList<>();

            if(resultSet!=null){

                while (resultSet.next()){
                    int id1 = resultSet.getInt("g_id");
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

                    game.setId(id1);
                    game.setName(name);
                    game.setType(type);
                    game.setPrice(price);
                    game.setDiscount(discount);
                    game.setIntroduction(introduction);
                    game.setTime(time);
                    game.setAge(age);
                    game.setP_id(p_id);
                    game.setP_name(p_name);

                    onlineGames.add(game);

                    System.out.println(game);
                }

                HttpSession session = req.getSession();
                session.setAttribute("onlineGames",onlineGames);
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
            resp.sendRedirect("http://localhost:8080/jsp/showMyGame.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户提交上架表
     * 传入g_name、g_type、g_price、g_discount、g_introduction、g_age、u_id;
     */
    public void toPutGame(HttpServletRequest req, HttpServletResponse resp) {

        //http://localhost:8080/user?method=toPutGame&g_name=0000&g_type=0000&g_price=999&g_discount=1&g_introduction=0000&g_age=12&u_id=1

        String g_name = req.getParameter("gameName");
        String g_type = req.getParameter("gameType");
        float g_price = Float.parseFloat(req.getParameter("gamePrice"));
        float g_discount = Float.parseFloat(req.getParameter("gameDiscount"));
        String g_introduction = req.getParameter("gameIntroduction");
        int g_age = Integer.parseInt(req.getParameter("gameAge"));
        int u_id = Integer.parseInt(req.getParameter("producerId"));

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql = "insert tb_put(put_time,g_name, g_type, g_price,g_discount,g_introduction,g_age,put_status,u_id) values " + "('" + new Date(new java.util.Date().getTime()) + "','" + g_name + "','" + g_type + "','" + g_price + "','" + g_discount + "','" + g_introduction + "','" + g_age + "',0,'" +u_id + "')";
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                resp.getWriter().print("true");
            } else {
                resp.getWriter().print("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
     * 用户提交下架表
     * 传入g_id、g_name、take_reason、u_id;
     */
    public void toTakeGame(HttpServletRequest req, HttpServletResponse resp) {
        //http://localhost:8080/user?method=toTakeGame&g_id=111&g_name=0000&take_reason=0000&u_id=1

        int g_id = Integer.parseInt(req.getParameter("gameId"));
        String g_name = req.getParameter("gameName");
        String take_reason = req.getParameter("offlineReason");
        int u_id = Integer.parseInt(req.getParameter("producerId"));

        System.out.println("访问提交下架表方法成功！"+g_id+","+g_name+","+take_reason+","+u_id);
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql = "insert tb_take(take_time,g_id,g_name,take_reason,take_status,u_id) values " + "('" + new Date(new java.util.Date().getTime()) + "','" + g_id + "','" + g_name + "','" + take_reason +  "',0,'" +u_id + "')";
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                resp.getWriter().print("true");
            } else {
                resp.getWriter().print("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
     * 用户提交修改表
     * 传入g_id,g_name、g_type、g_price、g_discount、g_introduction、g_time,g_age、u_id;
     */
    public void toChangeGame(HttpServletRequest req, HttpServletResponse resp) {
        // http://localhost:8080/user?method=toChangeGame&g_id=111&g_name=0000&g_type=515454&g_price=999&g_discount=1&g_introduction=0000&g_time=2021-01-11&g_age=12&u_id=1

        int g_id = Integer.parseInt(req.getParameter("gameId"));
        String g_name = req.getParameter("gameName");
        String g_type = req.getParameter("gameType");
        float g_price = Float.parseFloat(req.getParameter("gamePrice"));
        float g_discount = Float.parseFloat(req.getParameter("gameDiscount"));
        String g_introduction = req.getParameter("gameIntroduction");
        Date g_time = Date.valueOf(req.getParameter("gameCreateTime"));
        g_time=new Date(g_time.getTime());
        int g_age = Integer.parseInt(req.getParameter("gameAge"));
        int u_id = Integer.parseInt(req.getParameter("producerId"));

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //获取数据库操作对象
            statement = connection.createStatement();

            //定义sql
            String sql = "insert tb_change(change_time,g_id,g_name, g_type, g_price,g_discount,g_introduction,g_time,g_age,change_status,u_id) values " + "('" + new Date(new java.util.Date().getTime()) + "','" + g_id + "','" + g_name + "','" + g_type + "','" + g_price + "','" + g_discount + "','" + g_introduction + "','" + g_time + "','" + g_age + "',0,'" +u_id + "')";
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                resp.getWriter().print("true");
            } else {
                resp.getWriter().print("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
     * 根据游戏id获取选中的onlineGame
     * @param req
     * @param resp
     */
    public void toGetChsOnlineGame(HttpServletRequest req, HttpServletResponse resp) {

        String gameId = req.getParameter("gameId");

        System.out.println("选中的上架游戏的id："+gameId);

        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        Game chsOnlineGame = new Game();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.createStatement();
            String sql=" select * from tb_game where g_id ='"+gameId+"'";

            resultSet = statement.executeQuery(sql);


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


                    chsOnlineGame.setId(id);
                    chsOnlineGame.setName(name);
                    chsOnlineGame.setType(type);
                    chsOnlineGame.setPrice(price);
                    chsOnlineGame.setDiscount(discount);
                    chsOnlineGame.setIntroduction(introduction);
                    chsOnlineGame.setTime(time);
                    chsOnlineGame.setAge(age);
                    chsOnlineGame.setP_id(p_id);
                    chsOnlineGame.setP_name(p_name);

                }
                HttpSession session = req.getSession();
                //把集合对象存放到作用域
                session.setAttribute("chsOnlineGame",chsOnlineGame);
                System.out.println(chsOnlineGame);
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