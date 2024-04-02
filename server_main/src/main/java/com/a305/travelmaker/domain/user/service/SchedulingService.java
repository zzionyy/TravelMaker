package com.a305.travelmaker.domain.user.service;

import com.a305.travelmaker.domain.travel.entity.Travel;
import com.a305.travelmaker.domain.travel.repository.TravelRepository;
import com.a305.travelmaker.domain.user.service.UserService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final TravelRepository travelRepository;
    private final UserService userService;

    @Scheduled(cron = "0 0 0 * * *") // 자정마다 스케줄링 실행
    public void updateUsersBasedOnTravelDates() {
        LocalDate today = LocalDate.now();
        List<Travel> travels = travelRepository.findAll();

        for (Travel travel : travels) {
            LocalDate twoWeeksBeforeStartDate = travel.getStartDate().minusWeeks(2);
            if (!today.isBefore(twoWeeksBeforeStartDate) && today.isBefore(travel.getStartDate())) {
                updateUserStatusAfterCourse(travel.getUser().getId());
                updateFriendsStatus(travel.getFriends());
            } else if (today.isAfter(travel.getEndDate())) {
                updateUserStatusBeforeCourse(travel.getUser().getId());
                updateFriendsStatus(travel.getFriends());
            } else if (today.isAfter(travel.getStartDate()) || today.equals(travel.getStartDate())) {
                updateUserStatusOnCourse(travel.getUser().getId());
                updateFriendsStatus(travel.getFriends());
            }
        }
    }

    private void updateFriendsStatus(String friends) {
        if (friends != null && !friends.isEmpty()) {
            String[] friendIds = friends.split(",");
            for (String friendId : friendIds) {
                try {
                    Long userId = Long.parseLong(friendId.trim());
                    updateUserStatusAfterCourse(userId);
                } catch (NumberFormatException e) {
                    // 친구 ID가 유효하지 않은 경우 예외 처리
                    // 필요에 따라 로그를 남기거나 예외 처리를 수행합니다.
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateUserStatusAfterCourse(Long userId) {
        userService.updateUserStatusAfterCourse(userId);
    }

    private void updateUserStatusOnCourse(Long userId) {
        userService.updateUserStatusOnCourse(userId);
    }

    private void updateUserStatusBeforeCourse(Long userId) {
        userService.updateUserStatusBeforeCourse(userId);
    }
}
