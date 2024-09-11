package alpb.domain;

import alpb.domain.*;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository
    extends PagingAndSortingRepository<User, Long> {
        User findByEmailAndPassword(String email, String password);

        User findByUserIdx(Long userIdx);

        void deleteByUserIdx(Long userIdx);

    }
