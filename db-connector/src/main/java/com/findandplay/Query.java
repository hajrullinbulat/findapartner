package com.findandplay;

public interface Query {
   String test = "SELECT\n" +
           "  me.id                     AS me_id,\n" +
           "  me.user_name              AS my_name,\n" +
           "  a.id                      AS advert_id,\n" +
           "  a.advert_info             AS advert_info,\n" +
           "  s.id                      AS sport_id,\n" +
           "  s.sport_name              AS sport_name,\n" +
           "  ca.id                     AS my_adverts_id,\n" +
           "  ca.user_id                AS user_id_from_my_adverts,\n" +
           "  ca.checked_adverts_status AS status_of_checked_advert,\n" +
           "  ch_a.user_name            AS user_name_from_my_adverts,\n" +
           "  me_ch_sec.id              AS me_checked_sections_id,\n" +
           "  me_ch_sec.section_id      AS checked_sections_section_id,\n" +
           "  sec2.section_info         AS checked_sections_section_info,\n" +
           "  ch_sec.id                 AS checked_sections_id,\n" +
           "  ch_sec.user_id            AS checked_sections_user_id,\n" +
           "  ch_sec_u.user_name        AS checked_sections_user_name\n" +
           "FROM users me\n" +
           "  INNER JOIN adverts a ON a.advert_author_id = me.id\n" +
           "  INNER JOIN sports s ON a.advert_sport_id = s.id\n" +
           "  INNER JOIN checked_adverts ca ON a.id = ca.advert_id\n" +
           "  INNER JOIN users ch_a ON ca.user_id = ch_a.id\n" +
           "  --   INNER JOIN sections sec on sec.author_id=me.id \n" +
           "  --   INNER JOIN sports s2 on sec.sport_id=s2.id \n" +
           "  --   INNER JOIN checked_sections cs on sec.id=cs.section_id \n" +
           "  --   INNER JOIN users ch_s on cs.user_id=ch_s.id \n" +
           "  INNER JOIN checked_sections me_ch_sec ON me_ch_sec.user_id = me.id\n" +
           "  INNER JOIN sections sec2 ON me_ch_sec.section_id = sec2.id\n" +
           "  INNER JOIN checked_sections ch_sec ON ch_sec.section_id = sec2.id\n" +
           "  INNER JOIN users ch_sec_u ON ch_sec.user_id = ch_sec_u.id\n" +
           "--   INNER JOIN checked_sections all_sec on me_ch_sec. \n" +
           "--   INNER JOIN users ch_u_sec on me_ch_sec.user_id = ch_u_sec.id \n" +
           "WHERE me.msisdn = ?";
}
