package com.vvvv.test;

import java.sql.SQLException;
import java.util.List;

import com.vvvv.dao.BookTypeDAO;
import com.vvvv.entity.BookType;

public class TestBookTypeDAO {
    public static void main(String[] args) {
        TestBookTypeDAO testBookTypeDAO = new TestBookTypeDAO();
        //testBookTypeDAO.testSave();
        //testBookTypeDAO.testUpdate();
        //testBookTypeDAO.testUpdate1();
        //testBookTypeDAO.testDelete();
        //testBookTypeDAO.testFindAll();
        testBookTypeDAO.testFindById();
    }

    public void testSave() {
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        BookType bookType = new BookType(0, "历史", "历史小说");
        System.out.println(bookTypeDAO.save(bookType));
    }

    public void testFindById() {
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        BookType bookType = bookTypeDAO.findById(1);
        System.out.println(bookType);
    }

    public void testUpdate() {
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        BookType bookType = new BookType(0, "历史", "历史小说");
        bookType.setisDelete(1);
        bookType.setBookTypeId(9);
        System.out.println(bookTypeDAO.update(bookType));
    }

    public void testUpdate1() {
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        System.out.println(bookTypeDAO.updateisDelete(9));
    }

    public void testDelete() {
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        System.out.println(bookTypeDAO.delete(9));
    }

    public void testFindAll() {
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        List<BookType> bookTypes = bookTypeDAO.findAll();
        for (BookType bookType : bookTypes) {
            System.out.println(bookType);
        }
    }
}
