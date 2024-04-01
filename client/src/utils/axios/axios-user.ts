import { AxiosResponse } from 'axios';
import { instance, oauthInstance } from './axios-instance';

const kakaoauthentication = (code: string): Promise<AxiosResponse<KakaoAuthResponse>> => {
  return instance.post<KakaoAuthResponse>('login/oauth2/code/kakao', { code });
};

const getScheduleList = (): Promise<AxiosResponse<ScheduleResponse>> => {
  return oauthInstance.get<ScheduleResponse>('travel/list');
};

const getDiaryList = (): Promise<AxiosResponse<DiaryResponse>> => {
  return oauthInstance.get<DiaryResponse>('diary/list');
};
const deleteDiary = (travelId: number) => {
  return oauthInstance.delete(`travel/${travelId}`);
};
const getUserStatus = (): Promise<AxiosResponse<UserStatusResponse>> => {
  return oauthInstance.get<UserStatusResponse>('user/status');
};

export {
  kakaoauthentication,
  getScheduleList,
  getDiaryList,
  deleteDiary,
  getUserStatus,
  UserStatusResponse,
};

interface ApiResponse<T> {
  status: string;
  data: T;
}

type KakaoAuthResponse = ApiResponse<KakaoAuthData>;
type ScheduleResponse = ApiResponse<ScheduleData[]>;
type DiaryResponse = ApiResponse<DiaryData[]>;

interface KakaoAuthData {
  userId: number;
  nickName: string;
  profileUrl: string;
  status: string;
  tokenType: string;
  accessToken: string;
  expiresIn: number;
  refreshToken: string;
  refreshTokenExpiresIn: number;
}

interface ScheduleData {
  travelId: number;
  cityName: string;
  startDate: string;
  endDate: string;
  friendNameList: string[];
  imgUrl: string;
  status: 'BEFORE_DIARY' | 'AFTER_DIARY';
}

interface DiaryData {
  diaryId: number;
  name: string;
  startDate: string;
  endDate: string;
  imgUrls: string;
}

interface UserStatusResponse {
  status: string;
  data: {
    status: string;
    afterCourseResponse: {
      startDate: string;
      travelId: number;
      cityName: string;
      imgUrl: string;
    };
    onCourseResponse: {
      cityName: string;
      startDate: string;
      courseInfoList: [
        {
          destinationName: string;
          destinationImgUrl: string;
        },
      ];
    };
    birthCheck: boolean;
    genderCheck: boolean;
    diaryCheck: boolean;
  };
}
