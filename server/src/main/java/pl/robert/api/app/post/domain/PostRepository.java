package pl.robert.api.app.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);

    Post findFirstByOrderByIdDesc();
}
