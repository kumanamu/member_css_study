package com.my.member.dto;

import com.my.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {


    private Long id;
    private String name;
    private int age;
    private String address;

    public static MemberDto fromMemberEntity(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getAddress()
                );

    }
}



