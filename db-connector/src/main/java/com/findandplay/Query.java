package com.findandplay;

public interface Query {
   String test = "SELECT\n" +
            "  me.id as me_id,\n" +
            "  me.name as my_name,\n" +
            "  a.id as advert_id,\n" +
            "  a.info as advert_info,\n" +
            "  s.id as sport_id,\n" +
            "  s.name as sport_name,\n" +
            "  ca.id as my_adverts_id,\n" +
            "  ca.user_id as user_id_from_my_adverts,\n" +
            "  ch_a.name as user_name_from_my_adverts,\n" +
//            "  --   sec.info, s2.name, cs.user_id, ch_s.name,\n" +
            "  me_ch_sec.id as checked_sections_id,\n" +
            "  me_ch_sec.section_id as checked_sections_section_id,\n" +
            "  sec2.info as checked_sections_section_info,\n" +
            "  ch_sec.user_id as checked_sections_user_id,\n" +
            "  ch_sec_u.name as checked_sections_user_name\n" +
            "FROM users me\n" +
            "  INNER JOIN adverts a ON a.author_id = me.id\n" +
            "  INNER JOIN sports s ON a.sport_id = s.id\n" +
            "  INNER JOIN checked_adverts ca ON a.id = ca.advert_id\n" +
            "  INNER JOIN users ch_a ON ca.user_id = ch_a.id\n" +
//            "  --   INNER JOIN sections sec on sec.author_id=me.id\n" +
//            "  --   INNER JOIN sports s2 on sec.sport_id=s2.id\n" +
//            "  --   INNER JOIN checked_sections cs on sec.id=cs.section_id\n" +
//            "  --   INNER JOIN users ch_s on cs.user_id=ch_s.id\n" +
            "  INNER JOIN checked_sections me_ch_sec ON me_ch_sec.user_id = me.id\n" +
            "  INNER JOIN sections sec2 ON me_ch_sec.section_id = sec2.id\n" +
            "  INNER JOIN checked_sections ch_sec ON ch_sec.section_id =sec2.id\n" +
            "  INNER JOIN users ch_sec_u on ch_sec.user_id = ch_sec_u.id\n" +
//            "--   INNER JOIN checked_sections all_sec on me_ch_sec.\n" +
//            "--   INNER JOIN users ch_u_sec on me_ch_sec.user_id = ch_u_sec.id\n" +
//            "\n" +
            "WHERE me.msisdn = ?";
}
