SELECT 
CASE a.KANTON_CD
           WHEN 0 THEN '??'
           WHEN 1 THEN 'ZH'
           WHEN 2 THEN 'BE'
           WHEN 3 THEN 'LU'
           WHEN 4 THEN 'UR'
           WHEN 5 THEN 'SZ'
           WHEN 6 THEN 'OW'
           WHEN 7 THEN 'NW'
           WHEN 8 THEN 'GL'
           WHEN 9 THEN 'ZG'
           WHEN 10 THEN 'FR'
           WHEN 11 THEN 'SO'
           WHEN 12 THEN 'BS'
           WHEN 13 THEN 'BL'
           WHEN 14 THEN 'SH'
           WHEN 15 THEN 'AR'
           WHEN 16 THEN 'AI'
           WHEN 17 THEN 'SG'
           WHEN 18 THEN 'GR'
           WHEN 19 THEN 'AG'
           WHEN 20 THEN 'TG'
           WHEN 21 THEN 'TI'
           WHEN 22 THEN 'VD'
           WHEN 23 THEN 'VS'
           WHEN 24 THEN 'NE'
           WHEN 25 THEN 'GE'
           WHEN 26 THEN 'JU'
           WHEN 27 THEN 'FL'
           WHEN 28 THEN 'D'
           WHEN 29 THEN 'I'
           ELSE '?'
      END AS "kanton"
, avg(MONTHS_BETWEEN(current date, p.GEBURTS_DT) / 12)  as "alter" FROM TBPS_ADRESSE a, TBPS_PARTNER p
where a.PARTNER_NR = p.PARTNER_NR 
and a.LAND_CD = 19 		-- SCHWEIZ
and a.ART_CD = 1 		-- DOMIZILADRESSE
and p.ART_CD = 1 		-- PRIVATPERSON
and GEBURTS_DT != '0001-01-01'
group by  a.KANTON_CD 
with ur;
        
                                 