package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 1+N 문제 해결하기 위해 fetch join을 썼는데 엔티티 그래프로 바꾼 상황
    // fetch join, 엔티티 그래프는 둘 다 1+N 문제 해결이 가능한데
    // fetch join 대신 엔티티 그래프로 바꾼 이유가 뭘까?
    // 일단 보이는 이유는 쿼리문을 안쓰니 코드가 간결화가 되는것 같고, 찾아보니 엔티티 그래프는 @ 어노테이션만 붙이면 되니까 재사용과 표준화에 좋다고 한다.
    // 하지만 @EntityGraph는 left outer join 만을 지원한다.
    // 그래서 복잡한 쿼리나, 다중 조인은 fetch join을 사용한다.
    @EntityGraph(attributePaths = "user")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Optional<Todo> findByIdWithUser(Long todoId);

    int countById(Long todoId);
}
