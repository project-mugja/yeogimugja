package com.mugja.review.service;

import com.mugja.review.dto.Review;
import com.mugja.review.domain.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final String UPLOAD_DIR = "C:/ymUpload/reviewImg";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private ReviewRepository reviewRepository;

    /*
    보여줄 페이지, 한페이지에 보여줄 개수, hostId를 받아 리뷰 목록 보여줌
     */
    @Override
    public Page<Review> findReviews(int page, int size, Integer hostId) {
        Page<Review> reviewPage = reviewRepository.findByHostIdOrderByWriteDateDesc(
                hostId,
                PageRequest.of(page, size)
        );
        return reviewPage;
    }

    /*
    리뷰 작성

    이미지 파일 크기 설정 아직 안했음..
     */
    @Override
    @Transactional
    public Review save(Review review) throws IOException {
        review.setWriteDate(new Date());

        //리뷰 객체에 이미지가 있는 경우에만 이미지 업로드를 실행
        if(review.getImage() != null && !review.getImage().isEmpty()) {
            
            //원본 이미지 이름
            String ogName = review.getImage().getOriginalFilename();
            
            //UUID 해쉬를 이용해 원본이름에 랜덤값을 더함
            String imageName = UUID.randomUUID().toString()+sdf.format(new Date())+ogName;
            
            //업로드 위치, 이미지 이름을 받아 파일 객체 생성
            File file = new File(UPLOAD_DIR,imageName);

            //업로드 폴더가 존재하지 않는 경우 폴더 생성
            if (!file.exists()) {
                file.mkdirs();
            }

            //이미지 업로드
            review.getImage().transferTo(file);
            
            //리뷰 객체에 이미지 경로 저장
            review.setImgPath(UPLOAD_DIR + imageName);
        }
        //db에 리뷰 저장
        reviewRepository.save(review);
        return review;
    }

    //리뷰 삭제
    @Override
    @Transactional
    public void delete(Integer rvId, Integer memId) throws IOException {

        //리뷰id 또는 멤버id가 null인 경우 익셉션
        if(rvId==null || memId==null) {
            throw new IllegalArgumentException("rvId and memId must not be null");
        }


        if(rvId.equals(memId)){
            reviewRepository.deleteByRvId(rvId);
        }else{
            throw new IllegalArgumentException("rvId and memId must match");
        }
    }
}
