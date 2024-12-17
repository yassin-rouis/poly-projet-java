package com.kayr.projetjava.polyprojetjava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Database {
    private static Connection cnx;

    public static void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/kayr", "root", "");
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<User>();

        PreparedStatement p = cnx.prepareStatement("SELECT * FROM kayr.users");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password")));
        }

        return users;
    }

    public static boolean login(String email, String password) throws SQLException {
        PreparedStatement p = cnx.prepareStatement("SELECT * FROM kayr.users WHERE email=? AND password=?");
        p.setString(1, email);
        p.setString(2, password);
        ResultSet rs = p.executeQuery();

        return rs.next();
    }

    public static boolean existsUser(String email) throws SQLException {
        PreparedStatement p = cnx.prepareStatement("SELECT * FROM kayr.users WHERE email=?");
        p.setString(1, email);
        ResultSet rs = p.executeQuery();

        return rs.next();
    }

    public static int registerUser(String email, String password, String name) throws SQLException {
        PreparedStatement p = cnx.prepareStatement("INSERT INTO kayr.users (email, password, name) VALUES (?,?,?)");
        p.setString(1, email);
        p.setString(2, password);
        p.setString(3, name);
        return p.executeUpdate();
    }


    public static int registerArticle(String name, Double prix) throws SQLException {
        PreparedStatement p = cnx.prepareStatement("INSERT INTO kayr.articles (name, prix) VALUES (?,?)");
        p.setString(1, name);
        p.setDouble(2, prix);
        return p.executeUpdate();
    }
    public static int registerArticle(String name, Double prix, String categorie) throws SQLException {
        PreparedStatement p = cnx.prepareStatement("INSERT INTO kayr.articles (name, prix, categorie) VALUES (?,?,?)");
        p.setString(1, name);
        p.setDouble(2, prix);
        p.setString(3, categorie);
        return p.executeUpdate();
    }

    public static List<Article> getArticles() throws SQLException {
        List<Article> articles = new ArrayList<Article>();

        PreparedStatement p = cnx.prepareStatement("SELECT * FROM kayr.articles");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            articles.add(new Article(rs.getInt("id"), rs.getString("name"), rs.getDouble("prix"), rs.getString("categorie")));
        }

        return articles;
    }

    public static boolean removeArticle(int id) throws SQLException {
        PreparedStatement p = cnx.prepareStatement("DELETE FROM kayr.articles WHERE id=?");
        p.setInt(1, id);
        return p.executeUpdate() > 0;
    }

}
