ALTER VIEW [dbo].[VWKPIMPMRSLT](COMP_NO, PLANT, YYYYMM, MTPOINT, PVALUE, AVALUE) AS
SELECT a.comp_no,
            e.plant,
            SUBSTRING (a.wkor_date, 1, 6) AS yyyymm,
            f.wo_type AS mtpoint,
            COUNT (*) AS pvalue,
            SUM (CASE WHEN a.wo_status = 'C' THEN 1 ELSE 0 END) AS avalue
       FROM taworkorder a,
            tapmsched b,
            tawoequip c,
            taequipment d,
            taeqloc e,
            tapmlst f
      WHERE     a.comp_no = b.comp_no
            AND a.pmsched_id = b.pmsched_id
            AND a.comp_no = c.comp_no
            AND a.wkor_id = c.wkor_id
            AND c.comp_no = d.comp_no
            AND c.equip_id = d.equip_id
            AND d.comp_no = e.comp_no
            AND d.eqloc_id = e.eqloc_id
            AND a.comp_no = f.comp_no
            AND a.pm_id = f.pm_id
            AND a.wo_status IN ('P', 'C')
            AND f.wo_type IS NOT NULL
            AND a.wkor_date IS NOT NULL
   GROUP BY a.comp_no,
            e.plant,
            SUBSTRING (a.wkor_date, 1, 6),
            f.wo_type
;
GO
