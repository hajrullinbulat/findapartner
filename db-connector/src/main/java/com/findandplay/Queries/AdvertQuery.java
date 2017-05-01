package com.findandplay.Queries;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
public interface AdvertQuery {
    //todo order by last action
    String searchAdvertsWithPaging =
            "SELECT\n" +
                    "  adv.id                 AS advert_id,\n" +
                    "  adv.advert_sport,\n" +
                    "  adv.advert_info,\n" +
                    "  adv.advert_min_level,\n" +
                    "  adv.advert_persons_count,\n" +
                    "  adv.advert_status,\n" +
                    "  adv.advert_created,\n" +
                    "  author.user_name       AS author_name,\n" +
                    "  author.msisdn          AS author_msisdn,\n" +
                    "  checked_user.id        AS checked_user_id,\n" +
                    "  checked_user.user_name AS checked_user_name,\n" +
                    "  checked_user.msisdn    AS checked_user_msisdn\n" +
                    "FROM adverts adv\n" +
                    "  INNER JOIN users author ON adv.advert_author_id = author.id\n" +
                    "  LEFT JOIN checked_adverts ch_adv ON adv.id = ch_adv.advert_id\n" +
                    "  LEFT JOIN users checked_user ON ch_adv.user_id = checked_user.id\n" +
                    "WHERE adv.advert_sport = ?\n" +
                    "ORDER BY adv.id\n" +
                    "LIMIT ?\n" +
                    "OFFSET ?";
}
