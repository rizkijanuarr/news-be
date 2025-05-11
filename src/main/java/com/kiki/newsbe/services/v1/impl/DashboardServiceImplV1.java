package com.kiki.newsbe.services.v1.impl;

import com.kiki.newsbe.repositories.CategoryRepository;
import com.kiki.newsbe.repositories.PostRepository;
import com.kiki.newsbe.repositories.PostViewsRepository;
import com.kiki.newsbe.repositories.auth.UserRepository;
import com.kiki.newsbe.response.v1.DashboardResponseV1;
import com.kiki.newsbe.services.v1.DashboardServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImplV1 implements DashboardServiceV1 {

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostViewsRepository postViewsRepository;

    @Override
    public List<DashboardResponseV1> getListDataDashboard() {
        DashboardResponseV1.PostViewsResponse postViewsResponse = buildPostViewsResponse();

        DashboardResponseV1 dashboard = DashboardResponseV1.builder()
                .categories(categoryRepository.countActiveCategories())
                .posts(postRepository.countActivePosts())
                .users(userRepository.countActiveUsers())
                .postViews(postViewsResponse)
                .build();

        return List.of(dashboard);
    }

    private DashboardResponseV1.PostViewsResponse buildPostViewsResponse() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        Date date = Date.from(thirtyDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Map<String, Object>> postViews = postViewsRepository.findPostViewsGroupedByDay(date);

        List<Integer> counts = new ArrayList<>();
        List<String> days = new ArrayList<>();

        if (postViews != null && !postViews.isEmpty()) {
            for (Map<String, Object> result : postViews) {
                counts.add(((Number) result.get("count")).intValue());
                days.add(result.get("day").toString());
            }
        }

        return DashboardResponseV1.PostViewsResponse.builder()
                .count(counts)
                .days(days)
                .build();
    }
}
