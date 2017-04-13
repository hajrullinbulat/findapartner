package com.findandplay.entity;

import com.findandplay.enums.CheckStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "CheckedAdvert.user",
                attributeNodes = {
                        @NamedAttributeNode("user")
                }
        ),
        @NamedEntityGraph(
                name = "CheckedAdvert.advert",
                attributeNodes = {
                        @NamedAttributeNode("advert")
                }
        )
})
@Table(name = "checked_adverts")
public class CheckedAdvertByUserEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AdvertEntity advert;

    private LocalDateTime checked;
    @Enumerated(EnumType.STRING)
    private CheckStatus status;

}