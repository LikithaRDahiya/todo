package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dto.TodoTask;
import dto.TodoUser;

public class TodoDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void saveUser(TodoUser user) {
		transaction.begin();
		manager.persist(user);
		transaction.commit();

	}

	public List<TodoUser> findByEmail(String email) {
		return manager.createQuery("select x from TodoUser x where email=?1").setParameter(1, email).getResultList();
	}

	public void addTaskindb(TodoTask task) {
		transaction.begin();
		manager.persist(task);
		transaction.commit();
	}

	public List<TodoTask> fetchTaskByUser(int id) {
		return manager.createQuery("select x from TodoTask x where user_id=?1").setParameter(1, id).getResultList();
	}

	public void updateTask(TodoTask task) {
		transaction.begin();
		manager.merge(task);
		transaction.commit();

	}

	public List<TodoTask> fetchTaskById(int id) {
		return manager.createQuery("select x from TodoTask x where id=?1").setParameter(1, id).getResultList();
	}

	public void deletetaskById(TodoTask task1) {
		transaction.begin();
		manager.remove(task1);
		transaction.commit();
		
	}

}
