package connectDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

public class DatabaseManager implements AutoCloseable {
    private static final String PERSISTENCE_UNIT_NAME = "KaraokeAplus_BTL MSSQL";
    @PersistenceUnit(unitName = PERSISTENCE_UNIT_NAME)
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static DatabaseManager instance;

    private DatabaseManager() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public static  void connect() {
        em = emf.createEntityManager();
    }

    public void connectIfNeeded() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
    }

    public boolean isConnected() {
        return em != null && em.isOpen();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    public static void main(String[] args) {
        try (DatabaseManager db = DatabaseManager.getInstance()) {
            db.connect();
            if (db.isConnected()) {
                System.out.println("Kết nối thành công");
                // Thực hiện các chức năng của bạn tại đây
            } else {
                System.out.println("Kết nối thất bại");
            }
        }
    }
}