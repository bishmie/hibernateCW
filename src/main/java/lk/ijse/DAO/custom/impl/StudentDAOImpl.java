package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student student) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);


        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Student student) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        List<Student> list = session.createQuery("from Student ", Student.class).list();

        String hql=("from Student");
        Query query = session.createQuery(hql);
        ArrayList<Student> studentlist = (ArrayList<Student>) query.list();
        transaction.commit();
        session.close();
        return studentlist;

    }

    @Override
    public boolean delete(String studentId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql= ("delete from Student where studentId = :s_id");
        Query query = session.createQuery(hql);
        query.setParameter("s_id",studentId);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql =("from Student where studentId = :s_id");
        Query query = session.createQuery(hql);
        query.setParameter("s_id",id);

        List<Student> stlist = query.list();

        Student stud=null;
       for(Student st : stlist){
        stud= new Student(st.getStudentId(),st.getFirstname(),st.getLastname(),st.getAddress(),st.getContact());
        }

        transaction.commit();
       session.close();

        return stud;
    }
}
