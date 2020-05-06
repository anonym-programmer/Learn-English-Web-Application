package pl.robert.api.app.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class UserInMemoryRepository implements UserRepository {

    private final ConcurrentHashMap<String, User> db;

    public UserInMemoryRepository(ConcurrentHashMap<String, User> db) {
        this.db = db;
    }

    @Override
    public void save(User user) {
        db.put(user.getEmail(), user);
    }

    @Override
    public void delete(User user) {
        db.remove(user.getEmail());
    }

    @Override
    public int count() {
        return db.size();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(db.values()));
    }

    @Override
    public Optional<User> findById(long id) {
        return db.values()
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public User findByUsername(String username) {
        return db.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public User findByEmail(String email) {
        return db.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public String findRandomUsername() {
        return null;
    }
}

