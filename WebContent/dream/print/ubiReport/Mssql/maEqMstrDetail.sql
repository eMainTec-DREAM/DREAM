$DataSet0
SELECT 
      x.item_no 																						itemNo
    , '{imagePath}' 																						imgPath
	, x.description 																					equip
    , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id) 		eqLoc
    , (SELECT a.full_Desc FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id) 		eqCtg
    , x.maker 																							maker
    , x.model_no 																						model
    , dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','{loginUser.langId}')  									eqStatus 
    , CASE WHEN x.setup_date IS NULL THEN '-'
    	   ELSE SUBSTRING(x.setup_date,1,4)+'-'+
             	SUBSTRING(x.setup_date,5,2)+'-'+
             	SUBSTRING(x.setup_date,7,2)
           END																							setupDate
    , CASE WHEN x.disUsed_date IS NULL THEN '-'
    	   ELSE SUBSTRING(x.disUsed_date,1,4)+'-'+
             	SUBSTRING(x.disUsed_date,5,2)+'-'+
             	SUBSTRING(x.disUsed_date,7,2)
           END																							disusedDate
	, CASE WHEN x.util_capa IS NULL THEN '-' 
		   ELSE x.util_capa 
		   END																							utility
    , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) 		dept
    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.main_mng_id ) 		mainMng
    , x.is_law_eq 																						isLawEq
    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.sub_mng_id ) 		subMng
    , dbo.SFACODE_TO_DESC(x.pmi_action_type,'PMI_ACTION_TYPE','SYS','','{loginUser.langId}')  						siaGrade
	, x.tag_no																							tagNo
    , x.serial_no 																						serialNo
	, x.as_vendor 																						asName
    , x.as_name  																						asManager
    , x.as_phone  																						asNum
    , dbo.SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS','','{loginUser.langId}')  									eqGrade        
    , x.ord_no																							ordNo
    , CASE WHEN x.capacity IS NULL THEN '-' 
    	   ELSE x.capacity 
    	   END																							capacity
    , x.weight																							usage
    , x.eq_spec																							eqSpec
    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant) 			plant
    , x.supplier																						supplier
    , REPLACE(REPLACE(x.remark,char(10),' '),char(13),' ') 												remark
FROM TAEQUIPMENT x
WHERE x.comp_no = '{loginUser.compNo}'
AND x.equip_id = {maEqMstrDetailDTO.equipId}
/
$DataSet1
SELECT 
		x.description equip
		,x.item_no itemNo
      	,x.maker maker
      	,x.model_no modelNo
      	,CASE WHEN x.setup_date IS NOT NULL THEN
	        SUBSTRING(x.setup_date,1,4)+'-'+
	        SUBSTRING(x.setup_date,5,2)+'-'+
	        SUBSTRING(x.setup_date,7,2)     ELSE '-' END	setupDate
      	,x.serial_no serialNo
      	,REPLACE(REPLACE(x.remark,char(10),''),char(13),' ') remark
FROM TAEQUIPMENT x
WHERE x.comp_no = '{loginUser.compNo}'
AND x.equip_id = {maEqMstrDetailDTO.equipId}
/
$DataSet2
SELECT row_number() over(order by z.wkor_date desc) seqNo, z.wkor_date wkorDate, z.asmb+z.description+'$enter'+z.perform+'$enter' description, z.emp, z.parts
FROM  (
SELECT 
		CASE WHEN y.wkor_date IS NOT NULL THEN
        SUBSTRING(y.wkor_date,1,4)+'-'+
        SUBSTRING(y.wkor_date,5,2)+'-'+
        SUBSTRING(y.wkor_date,7,2)     ELSE '-' END 												wkor_date 
	  , CASE WHEN (SELECT (SELECT b.full_desc
                    		 FROM TAEQASMB b
                       		WHERE b.comp_no = a.comp_no
                         	  AND b.eqasmb_id = a.eqasmb_id) 
					 FROM TAWOFAIL a
               	    WHERE a.comp_no = y.comp_no
                 	  AND a.wkor_id = y.wkor_id) is null THEN ''
			 ELSE (SELECT (SELECT b.full_desc
                        	 FROM TAEQASMB b
                       	    WHERE b.comp_no = a.comp_no
                              AND b.eqasmb_id = a.eqasmb_id)+ ', '
					 FROM TAWOFAIL a
               		WHERE a.comp_no = y.comp_no
                 	  AND a.wkor_id = y.wkor_id)	
			  END 																					asmb
       , y.description 																				description
       , REPLACE(REPLACE(y.perform,char(10),''),char(13),'  ') 										perform
       , (SELECT aa.emp_name FROM TAEMP aa WHERE aa.comp_no = y.comp_no AND aa.emp_id = y.emp_id) 	emp
	   , STUFF(( SELECT ',' +'('+b.part_no+')'+ b.description+' '+b.pt_size + '$enter'
				   FROM TAWOPARTS a INNER JOIN TAPARTS b
				                       ON a.comp_no = b.comp_no
					                  AND a.part_id = b.part_id 
				  WHERE 1=1
				    AND a.comp_no = x.comp_no
				    AND a.wkor_id = x.wkor_id
		 FOR XML PATH('') ),1,1,'') 																parts
FROM TAWOEQUIP x INNER JOIN TAEQHISTORY y
ON x.comp_no = y.comp_no
AND x.wkor_id = y.wkor_id
AND x.comp_no = '{loginUser.compNo}'
AND x.equip_id = {maEqMstrDetailDTO.equipId}
)z
/