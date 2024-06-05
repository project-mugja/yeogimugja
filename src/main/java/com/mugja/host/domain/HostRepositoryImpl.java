package com.mugja.host.domain;

import com.mugja.host.dto.HostWishDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HostRepositoryImpl implements HostRepositoryCustom{

    @PersistenceContext
    private EntityManager em;


    //숙소 검색
    @Override
    public Page<HostWishDTO> findByTagNative(String category, String search, Pageable pageable) {
        System.out.println("category: " + category);
        System.out.println("search: " + search);
        search = "%" + search + "%";
        String baseQuery =
                "select a.host_id as hostId, a.avgscore as avgScore, a.host_name as hostName, a.host_address as hostAddress " +
                "from host a left join tag c on a.host_id = c.host_id ";

        String where = "where ";
        String condition1 = "c.tag1 = :category ";
        String and = "and ";
        String searchQuery = "a.host_name like :search or a.host_address like :search ";

        String tagSearchQuery =
                "or tag2 like :search " +
                "or tag3 like :search " +
                "or tag4 like :search " +
                "or tag5 like :search " +
                "or tag6 like :search " +
                "or tag7 like :search " +
                "or tag8 like :search ";

        String orderBy = "order by " +
                "case " +
                "when a.host_name like :search then 0 " +
                "when a.host_name like :search then 1 " +
                "when a.host_name like :search then 2 " +
                "when a.host_name like :search then 2 " +
                "else 4 end, "+
                "case " +
                "when a.host_address like :search then 1 " +
                "when a.host_address like :search then 0 " +
                "when a.host_address like :search then 2 " +
                "when a.host_address like :search then 2 " +
                "else 4 end";
        String queryStr = category.equals("all")?
                baseQuery + where + searchQuery + tagSearchQuery + orderBy :
                baseQuery + where + condition1 + and + searchQuery + tagSearchQuery + orderBy;

        System.out.println("queryStr: " + queryStr);
        Query query = em.createNativeQuery(queryStr).setParameter("search", search);

        if(!category.equals("all")){
            query.setParameter("category", category);
        }

        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Object[]> results = query.getResultList();
        List<HostWishDTO> dtos = results.stream()
                .map(result -> new HostWishDTO(
                        (Integer) result[0],
                        (Byte) result[1],
                        (String) result[2],
                        (String) result[3],
                        null,
                        false,
                        category,
                        null
                        )
                )
                .collect(Collectors.toList());

        Query countQuery = em.createNativeQuery("select count(*) from ("+queryStr+") as countQuery").setParameter("search", search);
        if(!category.equals("all")){
            countQuery.setParameter("category", category);
        }
        long total = (Long) countQuery.getSingleResult();

        return new PageImpl<>(dtos, pageable, total);
    }
}
