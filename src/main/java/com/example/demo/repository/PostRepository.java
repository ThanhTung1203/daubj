package com.example.demo.repository;


import com.example.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT s FROM Post s WHERE s.user.id = :id and s.status like 'OnlyMe'")
    List<Post> nhuanhcho(Long id);
    List<Post> findByTitleContainingOrContentContaining(String keyword, String keyword1);

    List<Post> findAllByUserId(Long id);
    List<Post> findAllByOrderByLikesDesc();
    Page<Post> findTop4ByOrderByLikesDesc(PageRequest pageRequest);
}
