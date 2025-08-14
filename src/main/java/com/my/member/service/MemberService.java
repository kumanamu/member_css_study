package com.my.member.service;

import com.my.member.dto.MemberDto;
import com.my.member.entity.Member;
import com.my.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    //리포지토리 통해서 멤버리스트 가져오기
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    //리포지토리를 생성자 주입방법으로 가져오기
    public List<MemberDto> getAllList() {
        List<Member> memberList = repository.findAll();
        System.out.println(memberList);
        //비어있는 dto list를 생성
        List<MemberDto> dtoList = new ArrayList<>();

        //entity list를 dto list로 변환
//        for(int i=0; i<memberList.size(); i++){
//            //리스트에 있는 엔티티를 하나씩 읽어서
//            // dto에 담아주기
//            MemberDto dto = new MemberDto();
//            dto.setId(memberList.get(i).getId());
//            dto.setName(memberList.get(i).getName());
//            dto.setAge(memberList.get(i).getAge());
//            dto.setAddress(memberList.get(i).getAddress());
//            dtoList.add(dto);
//
//        }
        //fromMemberEntity 매서드로 작업하기
        return memberList
                .stream()
                .map(x -> MemberDto.fromMemberEntity(x))
                .toList();
//        return dtoList;
    }

    public void insertMember(MemberDto dto) {
        //서비스에서 dto를 엔티티로 바꾼다
//        Member member = new Member();
//        member.setName(dto.getName());
//        member.setAge(dto.getAge());
//        member.setAddress(dto.getAddress());
        //우리가 만든 toDto를 이용해서 멤버 엔티티 생성하기
        Member member = MemberDto.toDto(dto);
        //리포지토리를 이용해서 저장한다
        member = repository.save(member);
        System.out.println("================");
        System.out.println(member);

    }

    public void deleteMember(Long id) {
        //삭제처리
        repository.deleteById(id);

    }

    public MemberDto findMember(Long updateId) {
        //검색해보기
        //비어있는지 검사
        //없으면 null 리턴
        Member member = repository.findById(updateId).orElse(null);
        //멤버가 null인지 확인
        if (ObjectUtils.isEmpty(member)) {
            return null;
        } else {
            return MemberDto.fromMemberEntity(member);

    }
}

    public void updateMember(MemberDto dto) {

        //1.받은 dto 엔티티로 바꿔주기
        Member member = MemberDto.toDto(dto);

        //2. 수정 요청
        repository.save(member);
    }
}
