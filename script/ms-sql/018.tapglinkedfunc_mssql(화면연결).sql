


/** 2018-05-15 양소영 */
INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID, PAGE_ID, LINKEDFUNC_ID, FILE_NAME, LINKEDFUNC_METHOD, KEY_TYPE, KEY_NO, ORD_NO, IS_USE, REMARK, LINKEDFUNC_LOC) VALUES( NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maPmMstrPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maPmMstrPointDetail'), (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 10, 'N', '', '')

/** 2018-05-15 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRplMstrDetail') 
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment')
, 'maWoResultBmRplMstrDetail', 'machequipment', 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRplMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment')
, 'maWoResultCmRplMstrDetail', 'machequipment', 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRplMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment')
, 'maWoResultPmRplMstrDetail', 'machequipment', 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListRplEquipDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment')
, 'workPmListRplEquipDetail', 'machequipment', 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListInsEquipDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment')
, 'workPmListInsEquipDetail', 'machequipment', 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'toolsequipment')
, 'maWoResultPmGnlMstrDetail', 'toolsequipment', 'LABEL', 'eqTool', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListCalEquipDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'toolsequipment')
, 'workPmListCalEquipDetail', 'toolsequipment', 'LABEL', 'eqTool', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machEquipment')
, 'maWoResultPmInsMstrDetail', 'machEquipment', 'LABEL', 'equipNo', '20', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'toolsequipment')
, 'maWoResultPmInsMstrDetail', 'toolsequipment', 'LABEL', 'eqTool', '30', 'N');


/** 2018-05-15 김영주 */
UPDATE TAPGLINKEDFUNC SET linkedfunc_id = (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), linkedfunc_method = 'machequipment' WHERE linkedfunc_id IS NULL AND LOWER(linkedfunc_method) = LOWER('machequipment');

/** 2018-05-17 김영주 */
DELETE FROM TAPGLINKEDFUNC WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail') AND key_no = 'eqTool';

/** 2018-05-17 양소영 */
INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES( NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='workPmiPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='workPmiPointDetail'), (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 10, 'N');
INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES( NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='workPmiDInsPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), (SELECT file_name FROM tapage WHERE file_name ='workPmiDInsPointDetail'), (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 10, 'N');
INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES( NEXT VALUE FOR sqaPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmInsPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmInsPointDetail'), (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 10, 'N');

/** 2018-05-18 양소영 */
UPDATE TAPGLINKEDFUNC SET linkedfunc_loc = null WHERE key_no ='asmb';
DELETE TAPGLINKEDFUNC WHERE file_name IN ('workPmiPointDetail', 'workPmiDInsPointDetail');

/** 2018-05-23 김정우 */

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultMonthMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultMonthMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmInsMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmInsMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmRprMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmRprMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmRplMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmRplMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmCalEtcMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmCalEtcMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmClnMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmClnMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmOilMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmOilMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultBmRprMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultBmRprMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultBmRplMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultBmRplMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultBmOilMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultBmOilMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultCmRprMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultCmRprMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultCmRplMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultCmRplMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultTrEleMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultTrEleMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmGmMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmGmMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultBmBaseMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultBmBaseMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmGnlMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmGnlMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmSclMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmSclMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultPmPrsMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultPmPrsMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

INSERT INTO TAPGLINKEDFUNC(PGLINKEDFUNC_ID,PAGE_ID,LINKEDFUNC_ID,FILE_NAME,LINKEDFUNC_METHOD,KEY_TYPE,KEY_NO,ORD_NO,IS_USE) 
VALUES(next value for SQAPGLINKEDFUNC_ID, (SELECT page_id FROM tapage WHERE file_name ='maWoResultCmLocBaseMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'),  (SELECT file_name FROM tapage WHERE file_name ='maWoResultCmLocBaseMstrDetail'), 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no = 'ASMB'), 'LABEL', 'asmb', 20, 'N');

/**  2018-05-25 매일유업 반영 */
/**  2018-06-01 매일유업 반영 */

/** 2018-06-12 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccEquipList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptLccEquipList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccEquipDetailList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptLccEquipDetailList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccLocList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptLccLocList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccLocDetailList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptLccLocDetailList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptMtbfmttrEquipList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptMtbfmttrEquipList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptMtbfmttrEquipDetailList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptMtbfmttrEquipDetailList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptMtbfmttrLocList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptMtbfmttrLocList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptMtbfmttrLocDetailList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm')
, 'assetRptMtbfmttrLocDetailList', 'eqbm', 'LABEL', 'eqBm', '10', 'Y');

/** 2018-06-12 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR SQAPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name='maEqLocDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='machequipment'), 'maEqLocDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR SQAPGLINKEDFUNC_ID, (SELECT page_id FROM TAPAGE WHERE file_name='maEqCatalogDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='machequipment'), 'maEqCatalogDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');

/** 2018-06-18 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptinfo')
, 'maEqMstrPartDetail', 'ptinfo', 'LABEL', 'ptInfo', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptrechist')
, 'maEqMstrPartDetail', 'ptrechist', 'LABEL', 'ptRecHist', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptisshist')
, 'maEqMstrPartDetail', 'ptisshist', 'LABEL', 'ptIssHist', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptusehist')
, 'maEqMstrPartDetail', 'ptusehist', 'LABEL', 'ptUseHist', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptcurrstock')
, 'maEqMstrPartDetail', 'ptcurrstock', 'LABEL', 'stockQty', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptinfo')
, 'maEqMstrPartDetail', 'ptinfo', 'LABEL', 'ptInfo', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptrechist')
, 'maEqMstrPartDetail', 'ptrechist', 'LABEL', 'ptRecHist', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptisshist')
, 'maEqMstrPartDetail', 'ptisshist', 'LABEL', 'ptIssHist', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptusehist')
, 'maEqMstrPartDetail', 'ptusehist', 'LABEL', 'ptUseHist', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgPartDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'ptcurrstock')
, 'maEqMstrPartDetail', 'ptcurrstock', 'LABEL', 'stockQty', '50', 'Y');

/**  2018-06-19 매일유업 반영 */

/** 2018-06-20 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '70', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '80', 'Y');
      
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqMstrMoldDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '70', 'Y');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '70', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqToolMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '80', 'Y');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '70', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqBuildingMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '80', 'Y');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '70', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqUtilityMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '80', 'Y');
 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqPartMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '70', 'Y');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'assetListITDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'assetListITDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'assetListITDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'assetListITDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '40', 'N');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqinfopdf'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQINFOPDF'), 'BUTTON', 'EQINFOPDF', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '50', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '60', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '70', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'assetListGnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '80', 'N');

/**  2018-06-20 매일유업 반영 */
/**  2018-06-22 매일유업 반영 */
/**  2018-06-27 매일유업 반영 */

/** 2018-06-28 김영주 */
UPDATE TAPGLINKEDFUNC SET linkedfunc_loc = 'list' WHERE page_id IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('assetRptLccLocList','assetRptMtbfmttrLocList','assetRptMtbfmttrEquipList','assetRptLccEquipList'));
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'invtList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');

/** 2018-07-02 이지수 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMachMstrList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'maEqMachMstrList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');

/**  2018-07-02 매일유업 반영 */
/**  2018-07-03 매일유업 반영 */

/** 2018-07-05 이지수 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRplMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultBmRplMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRprMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultCmRprMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRplMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmRplMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmGnlMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmSclMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmPrsMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmPrsMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultTrEleMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultTrEleMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmInsMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'Y');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'maEqMstrList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'maEqToolMstrList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'assetListITList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'assetListGnMstrList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');

/** 2018-07-05 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqpdf'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQPDF'), 'BUTTON', 'EQPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'history'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='HISTORY'), 'BUTTON', 'HISTORY', '30', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'eqbm'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'LABEL', 'eqBm', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmmstr'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMMSTR'), 'BUTTON', 'PMMSTR', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmwo'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMWO'), 'BUTTON', 'PMWO', '60', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'useparts'),'maEqMoldMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='USEPARTS'), 'BUTTON', 'USEPARTS', '70', 'Y');

/**  2018-07-12 매일유업 반영 */

/** 2018-07-12 이지수 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtRepList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'repairxpdf'),'maPtRepList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REPAIRXPDF'), 'BUTTON', 'REPAIRXPDF', '10', 'Y', 'list');

/**  2018-07-13 DREAM 반영 */

/** 2018-07-13 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'partAdjStkTakeList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'partAdjStkTakeList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');

/** 2018-07-16 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRprMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultBmRprMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRprMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultCmRprMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'Y');
DELETE FROM TAPGLINKEDFUNC WHERE file_name='maWoResultPmGnlMstrDetail' AND linkedfunc_method='asmb';
DELETE FROM TAPGLINKEDFUNC WHERE file_name='maWoResultPmSclMstrDetail' AND linkedfunc_method='asmb';
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'toolsequipment'),'maWoResultPmSclMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'LABEL', 'eqTool', '10', 'Y');
DELETE FROM TAPGLINKEDFUNC WHERE file_name='maWoResultPmPrsMstrDetail' AND linkedfunc_method='asmb';
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmPrsMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'toolsequipment'),'maWoResultPmPrsMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'LABEL', 'eqTool', '10', 'Y');


/** 2018-07-17 양소영 */
delete FROM tapglinkedfunc WHERE file_name='partAdjStkTakeList' AND linkedfunc_method='exlupload';

/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */

/** 2018-07-20 이지수 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoPmwOvhResultMstrList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'maWoPmwOvhResultMstrList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');

/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */

/** 2018-08-02 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'partAdjStkTakeItemList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'partAdjStkTakeItemList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'N', 'list');

/** 2018-08-06 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqToolMstrDetail', 'audtrail', 'LABEL', 'audtrail', '80', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqBuildingMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqBuildingMstrDetail', 'audtrail', 'LABEL', 'audtrail', '80', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqUtilityMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqUtilityMstrDetail', 'audtrail', 'LABEL', 'audtrail', '80', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetListITDetail', 'audtrail', 'LABEL', 'audtrail', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListGnMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetListGnMstrDetail', 'audtrail', 'LABEL', 'audtrail', '40', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMoldMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMoldMstrDetail', 'audtrail', 'LABEL', 'audtrail', '80', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqVehicleMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqVehicleMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmOilMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmOilMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmRplMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListPtrlDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmListPtrlDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmRprMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmInsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmInsMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListDInsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmListDInsDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListCinsResultMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListCinsResultMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmClnMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmClnMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmTrMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmTrMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListRInsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmListRInsDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListEInsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmListEInsDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmCalEtcMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmCalEtcMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmGmMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmGmMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'Y');

/** 2018-08-07 국도 반영 */

/** 2018-08-08 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrInsList'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'exlupload'),'maEqMstrInsList',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'Y', 'list');

/** 2018-08-08 국도 반영 */
/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */

/** 2018-08-14 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'invtDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtPhaseDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'invtPhaseDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtItemsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'invtItemsDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtPrcDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'invtPrcDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrSpecDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrSpecDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrAsmbDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrAsmbDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrAssetDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrAssetDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrMoldDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldProdDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrMoldProdDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrMoldModHistDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrMoldModHistDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListTcycleDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetListTcycleDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListAssTlDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetListAssTlDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqPartMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqPartMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrItemsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqMstrItemsDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetListITSWDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetListITSWDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assAssetDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assAssetDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assAssetTlDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assAssetTlDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCatalogDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqCatalogDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgAsmbDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqCtgAsmbDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqCtgPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCtgSpecDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqCtgSpecDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqLocDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqLocDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptWorkHistDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetRptWorkHistDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqWorkDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'reqWorkDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');

/** 2018-08-17 국도 반영 */
/** 2018-08-21 DREAM 반영 */

/** 2018-08-21 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoReqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoReqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maQnaDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maQnaDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maQnaAnsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maQnaAnsDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maHelpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maHelpDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmGnlMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmRplMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRplCraftDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmRplCraftDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRplPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmRplPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultBmRprMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRprCraftDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultBmRprCraftDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultTrEleMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultTrEleMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultCmRprMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmGnlMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmSclMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmPrsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmPrsMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmCalEtcMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmCalEtcMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListPrsCaEqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListPrsCaEqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListPrsCavalDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListPrsCavalDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRprCraftDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultCmRprCraftDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultTrEleCraftDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultTrEleCraftDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmSclMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListCavalDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListCavalDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPrsCaCraftDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPrsCaCraftDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maMoldsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maMoldsDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'woPlanDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'woPlanDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'woPlanCraftDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'woPlanCraftDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'woPlanPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'woPlanPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maAppPrcDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maAppPrcDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'AssAssetScoreDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'AssAssetScoreDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListGnlCaEqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListGnlCaEqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmInsMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmInsPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'WorkPmiDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'WorkPmiDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmiPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiDInsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmiDInsDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListPtrlResultMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListPtrlResultMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListCinsPlanMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListCinsPlanMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workFmeaDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultBmRplMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoSchedDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoSchedDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmcPeriodDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workCalPmcPeriodDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmRInsPeriodDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workCalPmRInsPeriodDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtMstrEqPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtMstrEqPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtStckDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtStckDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtBuyReqHdrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtBuyReqHdrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtPurReqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtPurReqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtIssDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtIssDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtRecDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtRecDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtIssEmgDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtIssEmgDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'partIssEmgReqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'partIssEmgReqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'partIssEmgReqPartsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'partIssEmgReqPartsDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'partAdjStkTakeDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'partAdjStkTakeDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'partAdjStkTakeItemDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'partAdjStkTakeItemDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtRepDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtRepDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListCalEquipDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmListCalEquipDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmOilPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmOilPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmEqOilDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmEqOilDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCatalogDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqCatalogDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoReqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoReqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultCmRplMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmInsMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmiDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmiPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiDInsPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmiDInsPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListCinsPlanMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workListCinsPlanMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmClnMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPmClnMstrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultClnDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultClnDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmInsApprDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workCalPmInsApprDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maStdPointHdrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maStdPointHdrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtBuyReqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maStdPointHdrDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtFcRecDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtFcRecDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');

/* 2018-08-22 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListMsTimeDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmListMsTimeDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmMstrPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPmMstrPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqInvWorkDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'reqInvWorkDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRplPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultCmRplPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPartDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoResultPartDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
delete from TAPGLINKEDFUNC where page_id = (select page_id from tapage where file_name = 'maAppPrcList')
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPlanApprDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPlanApprDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaReqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workFmeaReqDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaResDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workFmeaResDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maStdPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maStdPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'Y');

/** 2018-08-22 매일유업 반영 */

/* 2018-08-22 김은아 */
DELETE FROM TAPGLINKEDFUNC WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtIssDetail');

/* 2018-08-22 김남현 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttMstrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttStckDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttStckDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttRecDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttRecDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttIssDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttIssDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttDisPartsDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttDisPartsDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttDisDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttDisDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'orgEmpTrainDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'orgEmpTrainDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'orgEmpCertDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'orgEmpCertDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEmpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEmpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maDeptDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maDeptDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWkCtrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWkCtrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maVendorDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maVendorDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtDeptBgDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtDeptBgDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtBudgetDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPtBudgetDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'docNoticeTargetDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'docNoticeTargetDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'docNoticeDeptDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'docNoticeDeptDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'docNoticeDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'docNoticeDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maDocCntrCdDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maDocCntrCdDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'docManualDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'docManualDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoDailyDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoDailyDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoDayRptDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maWoDayRptDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maAppLineUsrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maAppLineUsrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maAppLineDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maAppLineDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'persPrivDbSetContDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'persPrivDbSetContDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'persPrivDbSetDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'persPrivDbSetDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'certEmpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'certEmpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'certDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'certDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'eduEmpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'eduEmpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'eduDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'eduDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmSysEmpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmSysEmpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmSysEqAsmbDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmSysEqAsmbDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmSysEqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmSysEqDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmSysFDefDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmSysFDefDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmSysDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmSysDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmFfailItemDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmFfailItemDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmFfailDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmFfailDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmFuncEqItemDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmFuncEqItemDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmFuncEqDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmFuncEqDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmFmeaCrityDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmFmeaCrityDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmFmeaDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmFmeaDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmPmtaskCndtDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmPmtaskCndtDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmPmtaskMapDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmPmtaskMapDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmPmtaskDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmPmtaskDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUserDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUserDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUsrGrpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUsrGrpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'docCtgDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'docCtgDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtPrcTpItemDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'invtPrcTpItemDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtPrcTpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'invtPrcTpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assBasePointValDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assBasePointValDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assBasePointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assBasePointDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assBaseGradeDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assBaseGradeDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assBaseDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assBaseDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetStdProductEquipDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetStdProductEquipDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetStdProductDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetStdProductDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetStdCtctrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetStdCtctrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetStdAssetDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'assetStdAssetDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmCheckMonthlyUnitPriceDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmCheckMonthlyUnitPriceDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmCheckDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmCheckDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmStdCalibValDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmStdCalibValDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmStdCalibDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workPmStdCalibDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maLineRunPlanDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maLineRunPlanDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrWorkflowPhaseDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrWorkflowPhaseDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrWorkflowDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrWorkflowDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maLnGoalDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maLnGoalDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maCdUsrCdDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maCdUsrCdDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maCdUsrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maCdUsrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workLetCategEtcDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workLetCategEtcDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workLetCategDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workLetCategDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workLetCategPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workLetCategPointDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maFailureDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maFailureDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'failLibraryDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'failLibraryDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrPtWhEmpDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrPtWhEmpDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrPtWhDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrPtWhDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmCrityValDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmCrityValDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmCrityRowDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmCrityRowDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmCrityColDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmCrityColDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmCrityDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmCrityDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmTaskMapItemDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmTaskMapItemDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmTaskMapDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmTaskMapDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrIntfDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrIntfDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maResDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maResDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrExldataUploadDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrExldataUploadDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mcDataSelectScript'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mcDataSelectScript', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mcDataSelectDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mcDataSelectDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUserRptOrdDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUserRptOrdDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUserRptParamDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUserRptParamDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUserRptColDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUserRptColDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUserRptTableDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUserRptTableDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maUserRptDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maUserRptDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maBatchMngDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maBatchMngDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maDocLibDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maDocLibDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maDocImgDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maDocImgDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrExldataDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'mgrExldataDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maMailGroupDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maMailGroupDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'appReadyList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'appReadyList', 'audtrail', 'LABEL', 'audtrail', '10', 'N', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'rcmSysFEnvDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'rcmSysFEnvDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workServiceDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'workServiceDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maMailUsrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maMailUsrDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maMailDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maMailDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPttRtnDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maPttRtnDetail', 'audtrail', 'LABEL', 'audtrail', '10', 'N');

/* 2018-08-23 김은아 */
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListMsTimeDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmMstrPartDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'reqInvWorkDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRplPartDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPartDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPlanApprDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaReqDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaResDetail');
UPDATE TAPGLINKEDFUNC SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maStdPointDetail');

/** 2018-08-28 국도 반영 */

/* 2018-08-30 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRprMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultBmRprMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmCalEtcMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmCalEtcMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGmMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmGmMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmOilMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmOilMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmOilMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultBmOilMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmClnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmClnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmBaseMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultBmBaseMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRprMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultPmRprMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmLocBaseMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'maWoResultCmLocBaseMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '30', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmCalEtcMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'toolsequipment'),'maWoResultPmCalEtcMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'LABEL', 'eqTool', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultTrEleMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultTrEleMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '5', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGmMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultPmGmMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmOilMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultPmOilMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmOilMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultBmOilMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmClnMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultPmClnMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmBaseMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultBmBaseMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRprMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultPmRprMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmLocBaseMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'),'maWoResultCmLocBaseMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQMACH'), 'LABEL', 'eqMach', '10', 'N');

/* 2018-08-31 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmCalEtcMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmcpdf'),'maWoResultPmCalEtcMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMCPDF'), 'BUTTON', 'PMCPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmcpdf'),'maWoResultPmGnlMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMCPDF'), 'BUTTON', 'PMCPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmcpdf'),'maWoResultPmSclMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMCPDF'), 'BUTTON', 'PMCPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmPrsMstrDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmcpdf'),'maWoResultPmPrsMstrDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMCPDF'), 'BUTTON', 'PMCPDF', '20', 'Y');

/* 2018-09-03 양소영 */
DELETE FROM TAPGLINKEDFUNC WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='maEqToolMstrDetail') AND linkedfunc_method IN ('eqpdf','history','eqbm','pmmstr','pmwo','useparts');
UPDATE TAPGLINKEDFUNC SET ord_no='10' WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='maEqToolMstrDetail') AND linkedfunc_method='eqinfopdf';
UPDATE TAPGLINKEDFUNC SET ord_no='20' WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='maEqToolMstrDetail') AND linkedfunc_method='revisionhistory';
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail')
,(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wopmc'),'maEqToolMstrDetail'
,(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPMC'), 'LABEL', 'calibHist', '30', 'N');
 
/** 2018-09-04 국도 반영 */
/** 2018-09-06 매일유업 반영 */
/** 2018-09-10 국도 반영 */

/** 2018-09-13 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqCatalogPointDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'audtrail'), 'maEqCatalogPointDetail', 'audtrail', 'LABEL', 'audtrail', '90', 'N');


/* 2018-09-17 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPtBuyReqHdrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'vendor'), 'maPtBuyReqHdrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='VENDOR'), 'LABEL', 'vendor', '10', 'N');

/* 2018-09-20 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmRplMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmSclMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmLocBaseMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultCmLocBaseMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmRprMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultTrEleMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultTrEleMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmGnlMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmBaseMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultBmBaseMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmClnMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmClnMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultCmRplMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultCmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultCmRprMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmCalEtcMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmCalEtcMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmPrsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmPrsMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmInsMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmOilMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmOilMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGmMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultPmGmMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultBmRprMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmOilMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultBmOilMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultBmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'fmea'), 'maWoResultBmRplMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='FMEA'), 'MENU', 'WORKFMEA', '40', 'N');

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */

/* 2018-09-20 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreq'),'invtDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOREQ'), 'BUTTON', 'WOREQ', '10', 'N');

/** 2018-09-28 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoYearSchedDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoYearSchedDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoMonthSchedDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoMonthSchedDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoSchedDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoSchedDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmRprMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmRprMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmClnMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmClnMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmGmMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmGmMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmRplMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmRplMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmOilMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmOilMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmInsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmInsMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmGnlMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmGnlMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmSclMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmSclMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmPrsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmPrsMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmCalEtcMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultPmCalEtcMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultTrEleMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'maWoResultTrEleMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workListCinsResultMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workListCinsResultMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workListPtrlResultMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workListPtrlResultMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 

/** 2018-10-05 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment')
, 'maEqToolMstrDetail', 'machequipment', 'LABEL', 'pEquip', '40', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmInsApprDetail'), 
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'inssched'),'workCalPmInsApprDetail', 
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='INSSCHED'), 'BUTTON', 'INSSCHED', '10', 'N');

/** 2018-10-11 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmRInsPeriodDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workCalPmRInsPeriodDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmiDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workPmiDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
/** 2018-10-11 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmcPeriodDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workCalPmcPeriodDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 


/** 2018-10-12 국도화학 반영 */

/** 2018-10-12 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmPrsMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woplan'), 'maWoResultPmPrsMstrDetail', 'woplan', 'BUTTON', 'WOPLAN', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmCalEtcMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woplan'), 'maWoResultPmCalEtcMstrDetail', 'woplan', 'BUTTON', 'WOPLAN', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmGnlMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woplan'), 'maWoResultPmGnlMstrDetail', 'woplan', 'BUTTON', 'WOPLAN', '90', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmSclMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woplan'), 'maWoResultPmSclMstrDetail', 'woplan', 'BUTTON', 'WOPLAN', '90', 'N');

/** 2018-10-16 이근환 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id,page_id,linkedfunc_id,file_name,linkedfunc_method,key_type,key_no,ord_no,is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id,(SELECT page_id FROM tapage WHERE file_name='partRptPtMstrEqPartList'),(SELECT linkedfunc_id FROM talinkedfunc WHERE linkedfunc_no='WOHIST'),'partRptPtMstrEqPartList','wohist','LABEL','woHist','10','N');

/** 2018-10-17 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmiDInsDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workPmiDInsDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmiCInsDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workPmiCInsDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'N');

/** 2018-10-18 국도화학 반영 */

/** 2018-10-24 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmDInsMonthDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workCalPmDInsMonthDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workListCinsPlanMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='pmstd'), 'workListCinsPlanMstrDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PMSTD'), 'BUTTON', 'PMSTD', '10', 'Y'); 

/** 2018-10-25 국도화학 반영 */

/** 2018-10-25 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'assAssetTlDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'raprint'),'assAssetTlDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='RAPRINT'), 'BUTTON', 'RAPRINT', '10', 'N');

/** 2018-10-26 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, linkedfunc_loc, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maPtRecList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='exlupload'), 'maPtRecList', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'list', 'BUTTON', 'EXLUPLOAD', '10', 'N'); 

/** 2018-10-26 국도화학 반영 */

/** 2018-10-29 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqInvRecWorkDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'reqpdf'),'reqInvRecWorkDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REQPDF'), 'BUTTON', 'REQPDF', '10', 'N');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoReqDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'reqpdf'),'maWoReqDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REQPDF'), 'BUTTON', 'REQPDF', '10', 'N');

/** 2018-10-30 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmCalEtcMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmCalEtcMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmInsMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmInsMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmRprMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmRprMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmRplMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmRplMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmClnMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmClnMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmOilMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmOilMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmTrMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmTrMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maPmGmMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'maPmGmMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListRInsDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'workPmListRInsDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListEInsDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'workPmListEInsDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListDInsDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'workPmListDInsDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListPtrlDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'workPmListPtrlDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use)
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListCinsResultMstrDetail'),
(SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'revisionhistory'),'workListCinsResultMstrDetail',
(SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='REVISIONHISTORY'), 'BUTTON', 'REVISIONHISTORY', '10', 'Y');

/** 2018-10-30 매일유업 반영 */

/** 2018-10-31 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmiDInsDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'workPmiDInsDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmiDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'workPmiDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '20', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmiCInsDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'workPmiCInsDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '20', 'Y');

/** 2018-11-01 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmInsMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmInsMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmRprMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmRprMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmRplMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmRplMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmClnMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmClnMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmOilMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmOilMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultBmRprMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultBmRprMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultBmRplMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultBmRplMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultBmOilMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultBmOilMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultCmRprMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultCmRprMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultCmRplMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultCmRplMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultTrEleMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultTrEleMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmGmMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmGmMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmCalEtcMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmCalEtcMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmGnlMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmGnlMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmSclMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmSclMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultPmPrsMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultPmPrsMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultCmLocBaseMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultCmLocBaseMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultBmBaseMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='wopdf'), 'maWoResultBmBaseMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOPDF'), 'BUTTON', 'WOPDF', '50', 'Y');

/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */
/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */
/** 2018-11-15 국도화학 반영 */
/** 2018-11-16 국도화학 반영 */

/** 2018-11-19 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='invtDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='copy'), 'invtDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='COPY'), 'BUTTON', 'COPYCREATE', '5', 'N');

/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */

/** 2018-11-23 김정우 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(next value for sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultBmRplMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='woconfirmprint'), 'maWoResultBmRplMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOCONFIRMPRINT'), 'BUTTON', 'WOCONFIRMPRINT', '10', 'N');

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) 
VALUES(next value for sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultBmOilMstrDetail')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='woconfirmprint'), 'maWoResultBmOilMstrDetail'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='WOCONFIRMPRINT'), 'BUTTON', 'WOCONFIRMPRINT', '10', 'N');

/** 2018-11-28 국도화학 반영 */
/** 2018-11-29 국도화학 반영 */
/** 2018-12-03 국도화학 반영 */

/** 2018-12-04 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maDeptList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='exlupload'), 'maDeptList'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'N', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maPtMstrList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='exlupload'), 'maPtMstrList'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'N', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmListInsList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='exlupload'), 'workPmListInsList'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'N', 'list');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workPmListWorkList')
, (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='exlupload'), 'workPmListWorkList'
, (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EXLUPLOAD'), 'BUTTON', 'EXLUPLOAD', '10', 'N', 'list');

/** 2018-12-05 본사 DREAM 반영 */
/** 2018-12-06 매일유업 반영 */
/** 2018-12-14 국도화학 반영 */
/** 2018-12-17 10:08 국도화학 반영 */
/** 2018-12-17 12:20 국도화학 반영 */
/** 2018-12-19 11:00 국도화학 반영 */
/** 2018-12-19 17:25 국도화학 반영 */
/** 2018-12-20 17:10 국도화학 반영 */
/** 2018-12-21 17:50 국도화학 반영 */
/** 2018-12-28 14:20 국도화학 반영 */
/** 2019-01-03 고려용접봉(본사) 반영 */
/** 2019-01-07 국도화학(본사) 반영 */
/** 2019-01-11 국도화학 반영 */
/** 2019-01-17 국도화학 반영 */
/** 2019-01-22 고려용접봉(본사) 반영 */
/** 2019-01-22 국도화학 반영 */
/** 2019-01-25 국도화학 반영 */
/** 2019-01-30 국도화학 반영 */
/** 2019-01-31 국도화학 반영 */
/** 2019-02-14 국도화학 반영 */

/** 2019-02-12 이근환 */
INSERT INTO tapglinkedfunc(pglinkedfunc_id,page_id,linkedfunc_id,file_name,linkedfunc_method,key_type,key_no,ord_no,is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM tapage WHERE file_name='maWoResultBmRplMstrDetail'), (SELECT linkedfunc_id FROM talinkedfunc WHERE linkedfunc_no='WOPLAN'),'maWoResultBmRplMstrDetail','woplan','BUTTON','WOPLAN','140','N');
INSERT INTO tapglinkedfunc(pglinkedfunc_id,page_id,linkedfunc_id,file_name,linkedfunc_method,key_type,key_no,ord_no,is_use)
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM tapage WHERE file_name='maWoResultCmRplMstrDetail'), (SELECT linkedfunc_id FROM talinkedfunc WHERE linkedfunc_no='WOPLAN'),'maWoResultCmRplMstrDetail','woplan','BUTTON','WOPLAN','140','N');

/** 2019-02-19 매일유업 반영 */


/** 2019-02-19 김은아 */
INSERT INTO TAPGLINKEDFUNC (PGLINKEDFUNC_ID, PAGE_ID, LINKEDFUNC_ID, FILE_NAME, LINKEDFUNC_METHOD, KEY_TYPE, KEY_NO, ORD_NO, IS_USE)
VALUES (next value for SQAPGLINKEDFUNC_ID, (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'workPmiDetail'), (SELECT LINKEDFUNC_ID FROM TALINKEDFUNC WHERE LINKEDFUNC_NO = 'INSPDF'), 'workPmiDetail', (SELECT LINKEDFUNC_METHOD FROM TALINKEDFUNC WHERE LINKEDFUNC_NO = 'INSPDF'), 'BUTTON', 'insPdf', '300', 'Y');
UPDATE TAPGLINKEDFUNC SET IS_USE = 'N' where page_id = (select page_id from tapage where file_name = 'workPmiDetail') AND LINKEDFUNC_ID = (SELECT LINKEDFUNC_ID FROM TALINKEDFUNC WHERE LINKEDFUNC_NO = 'WOPDF');

/** 2019-02-20 국도화학 반영 */
/** 2019-02-26 국도화학 반영 */
/** 2019-03-05 매일유업 반영 */



/** 2019-02-12 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmInsApprDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='print'), 'workCalPmInsApprDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PRINT'), 'BUTTON', 'PDF', '10', 'N'); 

/** 2019-02-18 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmInsApprCompDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='print'), 'workCalPmInsApprCompDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PRINT'), 'BUTTON', 'PDF', '20', 'N'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmInsApprCompDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='audtrail'), 'workCalPmInsApprCompDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='AUDTRAIL'), 'LABEL', 'audtrail', '90', 'Y'); 

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmInsApprNOTDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='print'), 'workCalPmInsApprNOTDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='PRINT'), 'BUTTON', 'PDF', '10', 'N'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmInsApprNOTDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='audtrail'), 'workCalPmInsApprNOTDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='AUDTRAIL'), 'LABEL', 'audtrail', '90', 'Y'); 

/** 2019-03-11 이근환 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmwCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'workRptPmwCmptDetailList', 'machequipment', UPPER('LABEL'), 'equipment', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmwCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wo'), 'workRptPmwCmptDetailList', 'wo', UPPER('BUTTON'), 'WO', '20', 'Y', '선택한 작업의 상세화면을 조회', null);


/** 2019-03-11 이근환 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmwDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'workRptPmwDetailList', 'machequipment', UPPER('LABEL'), 'equipment', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmwDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wo'), 'workRptPmwDetailList', 'wo', UPPER('BUTTON'), 'WO', '20', 'Y', '선택한 작업의 상세화면을 조회', null);



/** 2019-03-12 이근환 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptWoPmwCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'workRptWoPmwCmptDetailList', 'machequipment', UPPER('LABEL'), 'equipment', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptWoPmwCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wo'), 'workRptWoPmwCmptDetailList', 'wo', UPPER('BUTTON'), 'WO', '20', 'Y', '선택한 작업의 상세화면을 조회', null);


/** 2019-03-12 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = UPPER('machequipment')), 'workRptPmiEquipCmptDetailList', 'workRptPmiEquipCmptDetailList', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = UPPER('wo')), 'workRptPmiEquipCmptDetailList', 'workRptPmiEquipCmptDetailList', UPPER('BUTTON'), 'WO', '20', 'Y', '선택한 작업의 상세화면을 조회', null);

/** 2019-03-12 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptEmWoGenDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'reqRptEmWoGenDetailList', 'machequipment', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', LOWER(''));
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptEmWoGenDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'woreqdetail'), 'reqRptEmWoGenDetailList', 'woreqdetail', UPPER('BUTTON'), UPPER('WOREQ'), '30', 'Y', '선택한 요청접수의 상세화면을 조회', LOWER(''));
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptEmWoGenDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wo'), 'reqRptEmWoGenDetailList', 'wo', UPPER('BUTTON'), UPPER('WO'), '20', 'Y', '선택한 작업의 상세화면을 조회', LOWER(''));

/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */


/** 2019-03-13 양소영 */
DELETE FROM TAPGLINKEDFUNC WHERE pagE_id=(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipCmptDetailList');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'workRptPmiEquipCmptDetailList', 'machequipment', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipCmptDetailList'),  (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wopmi'), 'workRptPmiEquipCmptDetailList', 'wopmi', UPPER('BUTTON'), UPPER('RESULT'), '20', 'Y', '선택한 점검 작업의 상세화면을 조회', null);



/** 2019-03-13 양소영 */
DELETE FROM TAPGLINKEDFUNC WHERE pagE_id=(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiCmptDetailList');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='machequipment'), 'workRptPmiCmptDetailList', 'machequipment', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiCmptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wopmi'), 'workRptPmiCmptDetailList', 'wopmi', UPPER('BUTTON'), 'RESULT', '20', 'Y', '선택한 점검의 작업 상세화면을 조회', null);


/** 2019-03-13 양소영 */
DELETE FROM TAPGLINKEDFUNC WHERE pagE_id=(SELECT page_id FROM TAPAGE WHERE file_name='workRptPmiEquipPlanDetailList');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipPlanDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'workRptPmiEquipPlanDetailList', 'machequipment', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipPlanDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wopmi'), 'workRptPmiEquipPlanDetailList', 'wopmi', UPPER('BUTTON'), 'RESULT', '20', 'Y', '선택한 작업의 상세화면을 조회', null);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipPlanDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pmstd'), 'workRptPmiEquipPlanDetailList', 'pmstd', UPPER('BUTTON'), 'PMSTD', '30', 'Y', '선택한 점검의 기준서를 조회', null);

/** 2019-03-19 매일유업 반영 */


/** 2019-03-26 김정우 */

INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use)
VALUES (NEXT VALUE FOR SQApglinkedfunc_id,  (select page_id from tapage where file_name='WoReqDetailFragment'),
    (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'WoReqDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='AssetDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'AssetDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='PmiDayDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'PmiDayDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='PmiPartDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'PmiPartDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='PmiPatrolDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'PmiPatrolDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='PmiRoutineDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'PmiRoutineDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='WoResDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'WoResDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='CalDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'CalDetailFragment', '','LABEL','eqTool','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='CalDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'CalDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='CalCalibGnlValueDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'CalCalibGnlValueDetailFragment', '','LABEL','eqTool','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='CalCalibPrsValueDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'CalCalibPrsValueDetailFragment', '','LABEL','eqTool','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='CalCalibSclValueFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'CalCalibSclValueFragment', '','LABEL','eqTool','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='CalCalibValueDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='EQTOOL'), 'CalCalibValueDetailFragment', '','LABEL','eqTool','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='InspectionDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'InspectionDetailFragment', '','LABEL','woHist','010','Y');
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id ,linkedfunc_id,file_name,linkedfunc_method,key_Type,key_no,ord_no,is_use) VALUES (NEXT VALUE FOR sqapglinkedfunc_id, (select page_id from tapage where file_name='UnplanDetailFragment'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WOHIST'), 'UnplanDetailFragment', '','LABEL','woHist','010','Y');

/** 2019-03-14 양소영 */
UPDATE TAPGLINKEDFUNC SET key_no='REPORTPRINT',ord_no='20' WHERE page_id = (SELECT page_id FROM tapage WHERE file_name='workCalPmInsApprDetail') AND key_no='PDF';
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='workCalPmInsApprCompDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='coverprint'), 'workCalPmInsApprCompDetail', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='COVERPRINT'), 'BUTTON', 'COVERPRINT', '10', 'N'); 

/** 2019-04-04 매일유업 반영 */



/** 2019-03-14 김은아 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptWoEndDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'workRptWoEndDetailList', 'machequipment', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', NULL);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workRptWoEndDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wo'), 'workRptWoEndDetailList', 'wo', UPPER('BUTTON'), UPPER('WO'), '20', 'Y', '선택한 작업의 상세화면을 조회', NULL);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptPreWoPlanDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'machequipment'), 'reqRptPreWoPlanDetailList', 'machequipment', UPPER('LABEL'), 'eqMach', '10', 'Y', '선택한 설비의 상세화면을 조회', NULL);
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptPreWoPlanDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'wo'), 'reqRptPreWoPlanDetailList', 'wo', UPPER('BUTTON'), UPPER('WO'), '20', 'Y', '선택한 작업의 상세화면을 조회', NULL);

/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */


/** 2019-04-17 김은아 */
update TAPGLINKEDFUNC set CRE_DATE = '20190327132010';
update TAPGLINKEDFUNC set UPD_DATE = '20190327132010';

/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */
/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-04-29 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workListEnergyMstrDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = ('pmstd')), 'workListEnergyMstrDetail', 'pmstd', UPPER('BUTTON'), UPPER('PMSTD'), '10', 'N', '측정기준주기를 확인', null);


/** 2019-04-30 이근환 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrUsrGrpPageAuthTabDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'pageauth'), 'mgrUsrGrpPageAuthTabDetail', 'pageauth', UPPER('BUTTON'), UPPER('PAGEAUTH'), '110', 'N', '화면권한설정', NULL);


/** 2019-05-20 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id ,file_name, linkedfunc_method ,key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc, site_type ,version_info ,basic_key_type ,basic_key_no) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMstrList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_no='WORKLISTPRINT') ,'maWoResultMstrList' ,'worklistprint' ,UPPER('BUTTON') ,UPPER('WORKLISTPRINT') ,'10' ,'N' ,'작업목록출력' ,'list', '' ,'' ,'BUTTON' ,'WORKLISTPRINT');

/** 2019-05-24 고려용접봉 반영 */
/** 2019-05-24 국도화학 반영 */
/** 2019-05-29 고려용접봉 반영 */
/** 2019-05-30 매일유업 반영 */
/** 2019-06-04 매일유업 반영 */


/** 2019-05-29 김영주 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, REMARK, linkedfunc_loc) 
VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoDailyDetail'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method = 'print'), 'maWoDailyDetail', 'print', UPPER('BUTTON'), UPPER('WODAILYPDF'), '120', 'N', '작업일지출력', NULL);

/** 2019-06-14 이지수 */
insert into tapglinkedfunc (pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, remark, cre_date) values (next value for sqapglinkedfunc_id, (select page_id from tapage where file_name = 'reqWorkDetail'), (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'REQPDF' ),'reqWorkDetail','reqpdf', 'BUTTON', 'REQPDF', '100', 'N', '의뢰서출력', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-06-17 경보제약 반영 */
/** 2019-06-17 고려용접봉 반영 */
/** 2019-06-26 고려용접봉 반영 */
/** 2019-06-26 국도화학 반영 */
/** 2019-06-26 매일유업 반영 */


/** 2019-06-04 양소영 */
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, linkedfunc_loc, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='assetRptLccUsDeptList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='eqbm'), 'assetRptLccUsDeptList', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), 'list', 'LABEL', 'eqBm', '110', 'N'); 
INSERT INTO TAPGLINKEDFUNC(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, linkedfunc_loc, key_type, key_no, ord_no, is_use) VALUES(NEXT VALUE FOR sqapglinkedfunc_id, (SELECT page_id FROM TAPAGE WHERE file_name='assetRptLccUsDeptDetailList'), (SELECT linkedfunc_id FROM TALINKEDFUNC WHERE linkedfunc_method='eqbm'), 'assetRptLccUsDeptDetailList', (SELECT linkedfunc_method FROM TALINKEDFUNC WHERE linkedfunc_no='EQBM'), NULL, 'LABEL', 'eqBm', '110', 'N'); 

/** 2019-06-28 매일유업 반영 */
/** 2019-07-10 경보제약 반영 */
/** 2019-07-18 경보제약 반영 */
/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */

/** 291 이지수 */
 insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'workRptWorkTypeRptByEmpList') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'WOHIST') , 'workRptWorkTypeRptByEmpList' , 'wohist','LINKED', 'WOHIST','110','Y','list','DREAM','2.01','LINKED', 'WOHIST', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 
 insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'workRptWorkTypeRptByEmpMonth') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'WOHIST') , 'workRptWorkTypeRptByEmpMonth' , 'wohist','LINKED', 'WOHIST','110','Y','detail','DREAM','2.01','LINKED', 'WOHIST', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 
 insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'workRptWorkTypeRptByEmpWoType') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'WOHIST') , 'workRptWorkTypeRptByEmpWoType' , 'wohist','LINKED', 'WOHIST','110','Y','detail','DREAM','2.01','LINKED', 'WOHIST', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 


/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */
/** 2019-08-13 경보제약 반영 */
/** 2019-08-19 경보제약 반영 */
/** 2019-08-22 경보제약 반영 */
 
 /** 297 영주 */
insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'maEqMachMstrList') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'BARCODEPRINT') , 'maEqMachMstrList' , 'printbarcode','LINKED', 'BARCODEPRINT','120','N','list','DREAM','2.50','LINKED', 'BARCODEPRINT', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 


/** 2019-08-23 국도화학 반영 */
/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */
/** 2019-08-29 매일유업 반영 */
/** 2019-09-05 경보제약 반영 */
/** 2019-09-11 경보제약 반영 */
/** 2019-09-18 본사 국도화학 반영 */
/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */

 /** 2019-10-10 김정우 */
 insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) values(next value for sqalinkedfunc_id, 'ANDROID_WOHIST', '', '작업이력', 'Y'); 
 update talinkedfunc set linkedfunc_method='', description='작업이력'   where linkedfunc_no='ANDROID_WOHIST' ;
 insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) values(next value for sqalinkedfunc_id, 'ANDROID_EQTOOL', '', '계측기', 'Y'); 
 update talinkedfunc set linkedfunc_method='', description='계측기'   where linkedfunc_no='ANDROID_EQTOOL' ;
 UPDATE TAPGLINKEDFUNC  SET key_type='LINKED' , key_no = 'ANDROID_WOHIST', linkedfunc_id =  (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='ANDROID_WOHIST')
WHERE file_name in (
    select file_name from tapage where service_type='ANT')
AND linkedfunc_id = (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='WOHIST');
UPDATE TAPGLINKEDFUNC  SET key_type='LINKED' , key_no = 'ANDROID_EQTOOL', linkedfunc_id =  (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='ANDROID_EQTOOL')
WHERE file_name in (
    select file_name from tapage where service_type='ANT')
AND linkedfunc_id = (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='EQTOOL');
UPDATE TAPGLINKEDFUNC  SET key_type='LINKED' , key_no = 'ANDROID_WOHIST', linkedfunc_id =  (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='ANDROID_WOHIST')
WHERE file_name in (
    select file_name from tapage where service_type='BEE')
AND linkedfunc_id = (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='WOHIST');
UPDATE TAPGLINKEDFUNC  SET key_type='LINKED' , key_no = 'ANDROID_EQTOOL', linkedfunc_id =  (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='ANDROID_EQTOOL')
WHERE file_name in (
    select file_name from tapage where service_type='BEE')
AND linkedfunc_id = (select linkedfunc_id from talinkedfunc where LINKEDFUNC_NO='EQTOOL');
DELETE FROM TAPGLINKEDFUNC WHERE page_id in (select page_id from tapage where service_type='ANT') AND linkedfunc_id  = (select linkedfunc_id from talinkedfunc where linkedfunc_no='ANDROID_WOHIST');

/** 2019-10-14 매일유업 반영 */
/** 2019-10-17 매일유업 반영 */
/** 2019-10-22 고려용접봉 반영 */

/*532 김남현*/
insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'assetRptWorkHistList') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'PDF') , 'assetRptWorkHistList' , 'pdf','LINKED', 'PDF','110','N','list','DREAM','2.01','LINKED', 'PDF', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 

insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'assetRptWorkHistDetail') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'WO') , 'assetRptWorkHistDetail' , 'wo','LINKED', 'WO','110','N','detail','DREAM','2.01','LINKED', 'WO', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 
insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'assetRptWorkHistDetail') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'EQMACH') , 'assetRptWorkHistDetail' , 'machequipment','LINKED', 'EQMACH','120','N','detail','DREAM','2.01','LINKED', 'EQMACH', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 
/** 2019-10-29 경보제약 반영 */
/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */

/** 682 + 은아 */
 insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'workPmListRInsEquipDetail') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'EQMACH') , 'workPmListRInsEquipDetail' , 'machequipment','LINKED', 'EQMACH','110','N','','DREAM','2.50','LINKED', 'EQMACH', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 

 /** 2019-11-14 고려용접봉 반영 */
 /** 2019-11-20 매일유업 반영 */
/** 2019-12-02 매일유업 반영 */ 
 /** 758 이근환 */
insert into tapglinkedfunc(pglinkedfunc_id, page_id, linkedfunc_id, file_name, linkedfunc_method, key_type, key_no, ord_no, is_use, linkedfunc_loc, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date )   
values(next value for sqapglinkedfunc_id,   (select page_id from tapage where file_name = 'maEqMstrDetail') ,   (select linkedfunc_id from talinkedfunc where linkedfunc_no = 'RPTEQINFOPDF') , 'maEqMstrDetail' , 'rpteqinfopdf','LINKED', 'RPTEQINFOPDF','110','N','','DREAM','2.01','LINKED', 'RPTEQINFOPDF', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') ); 

/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */

/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */
/** 2020-01-14 한국내화 kref 반영 */
/** 2020-01-17 매일유업 반영 */
