package dream.work.rpt.mabdpoint.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.mabdpoint.dao.MaBdPointDetailDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;

/**
 * 이상점검조치 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maBdPointDetailDAOTarget"
 * @spring.txbn id="maBdPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBdPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaBdPointDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param loginUser
     * @return
     */
    public MaBdPointDetailDTO findInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT	x.wongpoint_id 													AS woNgPointId		");
        query.append("         ,y.pm_type                                                       AS pmiType          ");
        query.append("         ,x.pm_wkor_id                                                    AS pmWkorId         ");
        query.append("         ,b.equip_id                                						AS equipId          ");
        query.append("		   ,x.pm_rep_method_type 											AS pmRepMethodType	");
        query.append("		   ,x.actual_date 													AS inspectDate		");
        query.append("		   ,(SELECT description																	");
        query.append("		   	 FROM TADEPT																		");
        query.append("		   	 WHERE comp_no = y.comp_no															");
        query.append("		   	  AND dept_id = y.dept_id) 										AS deptDesc			");
        query.append("		   ,(SELECT c.eqloc_id																	");
        query.append("			 FROM TAEQLOC c																		");
        query.append("			 WHERE c.comp_no = x.comp_no														");
        query.append("				AND c.eqloc_id = b.eqloc_id) 								AS eqLocId			");
        query.append("		   ,(SELECT c.full_desc																	");
        query.append("			 FROM TAEQLOC c																		");
        query.append("			 WHERE c.comp_no = x.comp_no														");
        query.append("			   AND c.eqloc_id = b.eqloc_id ) 								AS eqLocDesc		");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   	 FROM TAEMP																			");
        query.append("		   	 WHERE comp_no = y.comp_no															");
        query.append("		   	  AND emp_id = y.emp_id) 										AS woCraft			");
        query.append("		   ,b.description 													AS equipDesc		");
        query.append("		   ,b.item_no 														AS equipNo     		");
        query.append("		   ,(SELECT description																	");
        query.append("		   	 FROM TAEQASMB																		");
        query.append("		   	 WHERE comp_no = z.comp_no															");
        query.append("		   	  AND eqasmb_id = z.eqasmb_id)									AS asmbDesc			");
        query.append("		   ,z.check_point 													AS checkPoint		");
        query.append("		   ,z.check_method 													AS checkMethod		");
        query.append("		   ,z.fit_basis 													AS fitBasis			");
        query.append("		   ,SFACODE_TO_DESC(z.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+loginUser.getLangId()+"') 							");
        query.append("		   																	AS checkType		");
        query.append("		   ,z.check_min||' / '||z.check_basis_val||' / '||z.check_max||							");
        query.append("			CASE WHEN z.uom IS NULL THEN '' ELSE ' . ('||z.uom||')' END		AS checkUom			");
        query.append("		   ,x.result_value 													AS resultValue		");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 		");
        query.append("		   																AS pmPointRsltStatusDesc");
        query.append("		   ,y.wo_no 														AS inspectWoNo		");
        query.append("		   ,y.description 													AS inspectWoDesc	");
        query.append("		   ,x.remark 														AS inspectRemark	");
        query.append("		   ,x.pm_point_rep_status 											AS pmPointRepStatus	");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 			");
        query.append("		   																AS pmPointRepStatusDesc	");
        query.append("		   ,x.repair_desc 													AS repairDesc		");
        query.append("		   ,x.repair_date 													AS repairDate		");
        query.append("		   ,(SELECT a.description FROM TADEPT a 												");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id											");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 									");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 									");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept		");
        query.append("		   ,x.repair_by 													AS repairBy			");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   	 FROM TAEMP																			");
        query.append("		   	 WHERE comp_no = x.comp_no															");
        query.append("		   		AND emp_id = x.repair_by) 									AS repairName		");
        query.append("		   ,(SELECT a.param1 FROM TACDSYSD a 		                                            ");
        query.append("		     WHERE a.list_type = 'BM_TYPE' 		                                                ");
        query.append("		     AND a.key_no = 'BM_TYPE.'||(SELECT b.pm_type FROM TAWORKORDER b 					");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))		AS param1			");
        query.append("		   , x.woreq_id    													AS woreqId			");
        query.append("		   , x.woplan_wkor_id    											AS woplanId			");
        query.append("         , (SELECT a.wopoint_id                                                            	");
        query.append("              FROM TAWOPOINT    a                                                             ");
        query.append("             WHERE a.comp_no = y.COMP_NO                                                      ");
        query.append("               AND a.wkor_id = y.wkor_id                                                		");
        query.append("               AND a.pm_point_id = z.PM_POINT_ID)                         AS attachId			");
        query.append("FROM TAWONGPOINT x INNER JOIN TAWORKORDER y 													");
        query.append("ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id											");
        query.append("		INNER JOIN TAPMPOINT z 																	");
        query.append("		ON y.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id								");
        query.append("				INNER JOIN TAWOEQUIP a  														");
        query.append("              ON x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id     							");
        query.append("						INNER JOIN TAEQUIPMENT b 												");
        query.append("                      ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id           			");
        query.append("WHERE 1 = 1																					");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        MaBdPointDetailDTO maBdPointDetailDTO = 
        		(MaBdPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaBdPointDetailDTO()));
        
        return maBdPointDetailDTO;
    }

    public MaBdPointDetailDTO findPInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT	x.wongpoint_id 													AS woNgPointId		");
        query.append("         ,y.pm_type                                                       AS pmiType          ");
        query.append("         ,x.pm_wkor_id                                                    AS pmWkorId         ");
        query.append("         ,y.equip_id                                                      AS equipId          ");
        query.append("		   ,x.pm_rep_method_type 											AS pmRepMethodType	");
        query.append("		   ,x.actual_date 													AS inspectDate		");
        query.append("		   ,(SELECT description																	");
        query.append("		   		FROM TADEPT																		");
        query.append("		   		WHERE comp_no = y.comp_no														");
        query.append("		   		AND dept_id = y.dept_id) 									AS deptDesc			");
        query.append("		,(SELECT b.eqloc_id																		");
        query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
        query.append("			WHERE a.comp_no = b.comp_no															");
        query.append("				AND a.eqloc_id = b.eqloc_id														");
        query.append("				AND a.equip_id = y.equip_id														");
        query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocId			");
        query.append("		,(SELECT b.full_desc																	");
        query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
        query.append("			WHERE a.comp_no = b.comp_no															");
        query.append("				AND a.eqloc_id = b.eqloc_id														");
        query.append("				AND a.equip_id = y.equip_id														");
        query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocDesc		");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   		FROM TAEMP																		");
        query.append("		   		WHERE comp_no = y.comp_no														");
        query.append("		   		AND emp_id = y.emp_id) 										AS woCraft			");
        query.append("		,(SELECT a.description																	");
        query.append("			FROM TAEQUIPMENT a																	");
        query.append("			WHERE a.comp_no = y.comp_no															");
        query.append("				AND a.equip_id = y.equip_id		) 								AS equipDesc	");
        query.append("		,(SELECT a.item_no  																	");
        query.append("			FROM TAEQUIPMENT a																	");
        query.append("			WHERE a.comp_no = y.comp_no															");
        query.append("				AND a.equip_id = y.equip_id		) 								AS equipNo     	");
        query.append("		   ,(SELECT description																	");
        query.append("		   		FROM TAEQASMB																	");
        query.append("		   		WHERE comp_no = z.comp_no														");
        query.append("		   		AND eqasmb_id = z.eqasmb_id)								AS asmbDesc			");
        query.append("		   ,z.check_point 													AS checkPoint		");
        query.append("		   ,z.check_method 													AS checkMethod		");
        query.append("		   ,z.fit_basis 													AS fitBasis			");
        query.append("		   ,SFACODE_TO_DESC(z.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+loginUser.getLangId()+"') 							");
        query.append("		   																	AS checkType		");
        query.append("		   ,z.check_min||' / '||z.check_basis_val||' / '||z.check_max||							");
        query.append("			CASE WHEN z.uom IS NULL THEN '' ELSE ' . ('||z.uom||')' END		AS checkUom			");
        query.append("		   ,x.result_value 													AS resultValue		");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 		");
        query.append("		   																AS pmPointRsltStatusDesc");
        query.append("         ,(SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoNo             ");
        query.append("         ,(SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoDesc              ");
        query.append("		   ,x.remark 														AS inspectRemark	");
        query.append("		   ,x.pm_point_rep_status 											AS pmPointRepStatus	");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 			");
        query.append("		   																AS pmPointRepStatusDesc	");
        query.append("		   ,x.repair_desc 													AS repairDesc		");
        query.append("		   ,x.repair_date 													AS repairDate		");
        query.append("		   ,(SELECT a.description FROM TADEPT a 												");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id											");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 									");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 									");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept		");
        query.append("		   ,x.repair_by 													AS repairBy			");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   		FROM TAEMP																		");
        query.append("		   		WHERE comp_no = x.comp_no														");
        query.append("		   		AND emp_id = x.repair_by) 									AS repairName		");
        query.append("		   ,(SELECT a.param1 FROM TACDSYSD a 		                                            ");
        query.append("		     WHERE a.list_type = 'BM_TYPE' 		                                                ");
        query.append("		     AND a.key_no = 'BM_TYPE.'||(SELECT b.pm_type FROM TAWORKORDER b 					");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))		AS param1			");
        query.append("		   , x.woreq_id    													AS woreqId			");
        query.append("		   , x.woplan_wkor_id    											AS woplanId			");
        query.append("         , (SELECT a.pmptrlrsltpoint_id                                                       ");
        query.append("              FROM TAPMPTRLRSLTPOINT    a                                                     ");
        query.append("             WHERE a.comp_no = y.COMP_NO                                                      ");
        query.append("               AND a.pmptrlrsltlist_id = y.pmptrlrsltlist_id                                  ");
        query.append("               AND a.pm_point_id = z.PM_POINT_ID)                         AS attachId         ");
        query.append("FROM TAWONGPOINT x, TAPMPTRLRSLTLIST y, TAPMPOINT z											");
        query.append("WHERE x.comp_no = y.comp_no																	");
        query.append("AND y.comp_no = z.comp_no																		");
        query.append("AND x.pm_point_id = z.pm_point_id																");
        query.append("AND x.pmptrlrsltlist_id = y.pmptrlrsltlist_id													");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        MaBdPointDetailDTO maBdPointDetailDTO = 
        		(MaBdPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaBdPointDetailDTO()));
        
        return maBdPointDetailDTO;
    }

    public MaBdPointDetailDTO findRInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT	x.wongpoint_id 													AS woNgPointId		");
        query.append("         ,y.pm_type                                                       AS pmiType          ");
        query.append("         ,x.pm_wkor_id                                                    AS pmWkorId         ");
        query.append("         ,y.equip_id                                                      AS equipId          ");
        query.append("		   ,x.pm_rep_method_type 											AS pmRepMethodType	");
        query.append("		   ,x.actual_date 													AS inspectDate		");
        query.append("		   ,(SELECT description																	");
        query.append("		   		FROM TADEPT																		");
        query.append("		   		WHERE comp_no = y.comp_no														");
        query.append("		   		AND dept_id = y.dept_id) 									AS deptDesc			");
        query.append("		,(SELECT b.eqloc_id																		");
        query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
        query.append("			WHERE a.comp_no = b.comp_no															");
        query.append("				AND a.eqloc_id = b.eqloc_id														");
        query.append("				AND a.equip_id = y.equip_id														");
        query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocId			");
        query.append("		,(SELECT b.full_desc																	");
        query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
        query.append("			WHERE a.comp_no = b.comp_no															");
        query.append("				AND a.eqloc_id = b.eqloc_id														");
        query.append("				AND a.equip_id = y.equip_id														");
        query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocDesc		");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   		FROM TAEMP																		");
        query.append("		   		WHERE comp_no = y.comp_no														");
        query.append("		   		AND emp_id = y.emp_id) 										AS woCraft			");
        query.append("		,(SELECT a.description																	");
        query.append("			FROM TAEQUIPMENT a																	");
        query.append("			WHERE a.comp_no = y.comp_no															");
        query.append("				AND a.equip_id = y.equip_id		) 								AS equipDesc	");
        query.append("		,(SELECT a.item_no  																	");
        query.append("			FROM TAEQUIPMENT a																	");
        query.append("			WHERE a.comp_no = y.comp_no															");
        query.append("				AND a.equip_id = y.equip_id		) 								AS equipNo     	");
        query.append("		   ,(SELECT description																	");
        query.append("		   		FROM TAEQASMB																	");
        query.append("		   		WHERE comp_no = z.comp_no														");
        query.append("		   		AND eqasmb_id = z.eqasmb_id)								AS asmbDesc			");
        query.append("		   ,z.check_point 													AS checkPoint		");
        query.append("		   ,z.check_method 													AS checkMethod		");
        query.append("		   ,z.fit_basis 													AS fitBasis			");
        query.append("		   ,SFACODE_TO_DESC(z.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+loginUser.getLangId()+"') 							");
        query.append("		   																	AS checkType		");
        query.append("		   ,z.check_min||' / '||z.check_basis_val||' / '||z.check_max||							");
        query.append("			CASE WHEN z.uom IS NULL THEN '' ELSE ' . ('||z.uom||')' END		AS checkUom			");
        query.append("		   ,x.result_value 													AS resultValue		");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 		");
        query.append("		   																AS pmPointRsltStatusDesc");
        query.append("         ,(SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoNo            ");
        query.append("         ,(SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoDesc    ");
        query.append("		   ,x.remark 														AS inspectRemark	");
        query.append("		   ,x.pm_point_rep_status 											AS pmPointRepStatus	");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 			");
        query.append("		   																AS pmPointRepStatusDesc	");
        query.append("		   ,x.repair_desc 													AS repairDesc		");
        query.append("		   ,x.repair_date 													AS repairDate		");
        query.append("		   ,(SELECT a.description FROM TADEPT a 												");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id											");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 									");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 									");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept		");
        query.append("		   ,x.repair_by 													AS repairBy			");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   		FROM TAEMP																		");
        query.append("		   		WHERE comp_no = x.comp_no														");
        query.append("		   		AND emp_id = x.repair_by) 									AS repairName		");
        query.append("		   ,(SELECT a.param1 FROM TACDSYSD a 		                                            ");
        query.append("		     WHERE a.list_type = 'BM_TYPE' 		                                                ");
        query.append("		     AND a.key_no = 'BM_TYPE.'||(SELECT b.pm_type FROM TAWORKORDER b 					");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))		AS param1			");
        query.append("		   , x.woreq_id    													AS woreqId			");
        query.append("		   , x.woplan_wkor_id    											AS woplanId			");
        query.append("         ,(SELECT pminspoint_id																");
        query.append("           FROM TAPMINSPOINT																	");
        query.append("           WHERE comp_no = y.COMP_NO															");
        query.append("           AND PMINSLIST_ID = y.PMINSLIST_ID													");
        query.append("           AND pm_point_id = z.PM_POINT_ID)            					AS attachId	    	");
        query.append("FROM TAWONGPOINT x, TAPMINSLIST y, TAPMPOINT z												");
        query.append("WHERE x.comp_no = y.comp_no																	");
        query.append("AND y.comp_no = z.comp_no																		");
        query.append("AND x.pm_point_id = z.pm_point_id																");
        query.append("AND x.pminslist_id = y.pminslist_id															");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        MaBdPointDetailDTO maBdPointDetailDTO = 
        		(MaBdPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaBdPointDetailDTO()));
        
        return maBdPointDetailDTO;
    }

    public MaBdPointDetailDTO findDInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT	x.wongpoint_id 													AS woNgPointId		");
        query.append("         ,y.pm_type                                                       AS pmiType          ");
        query.append("         ,x.pm_wkor_id                                                    AS pmWkorId         ");
        query.append("         ,y.equip_id                                                      AS equipId          ");
        query.append("		   ,x.pm_rep_method_type 											AS pmRepMethodType	");
        query.append("		   ,x.actual_date 													AS inspectDate		");
        query.append("		   ,(SELECT description																	");
        query.append("		   		FROM TADEPT																		");
        query.append("		   		WHERE comp_no = y.comp_no														");
        query.append("		   		AND dept_id = y.dept_id) 									AS deptDesc			");
        query.append("		,(SELECT b.eqloc_id																	");
        query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
        query.append("			WHERE a.comp_no = b.comp_no															");
        query.append("				AND a.eqloc_id = b.eqloc_id														");
        query.append("				AND a.equip_id = y.equip_id														");
        query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocId			");
        query.append("		,(SELECT b.full_desc																	");
        query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
        query.append("			WHERE a.comp_no = b.comp_no															");
        query.append("				AND a.eqloc_id = b.eqloc_id														");
        query.append("				AND a.equip_id = y.equip_id														");
        query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocDesc		");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   		FROM TAEMP																		");
        query.append("		   		WHERE comp_no = y.comp_no														");
        query.append("		   		AND emp_id = y.emp_id) 										AS woCraft			");
        query.append("		,(SELECT a.description																	");
        query.append("			FROM TAEQUIPMENT a																	");
        query.append("			WHERE a.comp_no = y.comp_no															");
        query.append("				AND a.equip_id = y.equip_id		) 								AS equipDesc	");
        query.append("		,(SELECT a.item_no  																	");
        query.append("			FROM TAEQUIPMENT a																	");
        query.append("			WHERE a.comp_no = y.comp_no															");
        query.append("				AND a.equip_id = y.equip_id		) 								AS equipNo     	");
        query.append("		   ,(SELECT description																	");
        query.append("		   		FROM TAEQASMB																	");
        query.append("		   		WHERE comp_no = z.comp_no														");
        query.append("		   		AND eqasmb_id = z.eqasmb_id)								AS asmbDesc			");
        query.append("		   ,z.check_point 													AS checkPoint		");
        query.append("		   ,z.check_method 													AS checkMethod		");
        query.append("		   ,z.fit_basis 													AS fitBasis			");
        query.append("		   ,SFACODE_TO_DESC(z.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+loginUser.getLangId()+"') 							");
        query.append("		   																	AS checkType		");
        query.append("		   ,z.check_min||' / '||z.check_basis_val||' / '||z.check_max||							");
        query.append("			CASE WHEN z.uom IS NULL THEN '' ELSE ' . ('||z.uom||')' END		AS checkUom			");
        query.append("		   ,x.result_value 													AS resultValue		");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 		");
        query.append("		   																AS pmPointRsltStatusDesc");
        query.append("         ,(SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoNo             ");
        query.append("         ,(SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoDesc              ");
        query.append("		   ,x.remark 														AS inspectRemark	");
        query.append("		   ,x.pm_point_rep_status 											AS pmPointRepStatus	");
        query.append("		   ,SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 			");
        query.append("		   																AS pmPointRepStatusDesc	");
        query.append("		   ,x.repair_desc 													AS repairDesc		");
        query.append("		   ,x.repair_date 													AS repairDate		");
        query.append("		   ,(SELECT a.description FROM TADEPT a 												");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id											");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 									");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 									");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept		");
        query.append("		   ,x.repair_by 													AS repairBy			");
        query.append("		   ,(SELECT emp_name																	");
        query.append("		   		FROM TAEMP																		");
        query.append("		   		WHERE comp_no = x.comp_no														");
        query.append("		   		AND emp_id = x.repair_by) 									AS repairName		");
        query.append("		   ,(SELECT a.param1 FROM TACDSYSD a 		                                            ");
        query.append("		     WHERE a.list_type = 'BM_TYPE' 		                                                ");
        query.append("		     AND a.key_no = 'BM_TYPE.'||(SELECT b.pm_type FROM TAWORKORDER b 					");
        query.append("                             WHERE b.wongpoint_id = x.wongpoint_id))		AS param1			");
        query.append("		   , x.woreq_id    													AS woreqId			");
        query.append("		   , x.woplan_wkor_id    											AS woplanId			");
        query.append("         ,(SELECT a.pminsdpoint_id															");
        query.append("           FROM TAPMINSDPOINT	a																");
        query.append("           WHERE a.comp_no = y.COMP_NO														");
        query.append("           AND a.PMINSDLIST_ID = y.PMINSDLIST_ID												");
        query.append("           AND a.pm_point_id = z.PM_POINT_ID)            					AS attachId	    	");
        query.append("FROM TAWONGPOINT x, TAPMINSDLIST y, TAPMPOINT z												");
        query.append("WHERE x.comp_no = y.comp_no																	");
        query.append("AND y.comp_no = z.comp_no																		");
        query.append("AND x.pm_point_id = z.pm_point_id																");
        query.append("AND x.pminsdlist_id = y.pminsdlist_id															");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        MaBdPointDetailDTO maBdPointDetailDTO = 
        		(MaBdPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaBdPointDetailDTO()));
        
        return maBdPointDetailDTO;
    }
    
    public MaBdPointDetailDTO findCInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT	x.wongpoint_id 													AS woNgPointId		");
    	query.append("         ,y.pm_type                                                       AS pmiType          ");
    	query.append("         ,x.pm_wkor_id                                                    AS pmWkorId         ");
    	query.append("         ,y.equip_id                                                      AS equipId          ");
    	query.append("		   ,x.pm_rep_method_type 											AS pmRepMethodType	");
    	query.append("		   ,x.actual_date 													AS inspectDate		");
    	query.append("		   ,(SELECT description																	");
    	query.append("		   		FROM TADEPT																		");
    	query.append("		   		WHERE comp_no = y.comp_no														");
    	query.append("		   		AND dept_id = y.dept_id) 									AS deptDesc			");
    	query.append("		,(SELECT b.eqloc_id																	");
    	query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
    	query.append("			WHERE a.comp_no = b.comp_no															");
    	query.append("				AND a.eqloc_id = b.eqloc_id														");
    	query.append("				AND a.equip_id = y.equip_id														");
    	query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocId			");
    	query.append("		,(SELECT b.full_desc																	");
    	query.append("			FROM TAEQUIPMENT a, TAEQLOC b														");
    	query.append("			WHERE a.comp_no = b.comp_no															");
    	query.append("				AND a.eqloc_id = b.eqloc_id														");
    	query.append("				AND a.equip_id = y.equip_id														");
    	query.append("				AND a.comp_no = y.comp_no		) 							AS eqLocDesc		");
    	query.append("		   ,(SELECT emp_name																	");
    	query.append("		   		FROM TAEMP																		");
    	query.append("		   		WHERE comp_no = y.comp_no														");
    	query.append("		   		AND emp_id = y.emp_id) 										AS woCraft			");
    	query.append("		,(SELECT a.description																	");
    	query.append("			FROM TAEQUIPMENT a																	");
    	query.append("			WHERE a.comp_no = y.comp_no															");
    	query.append("				AND a.equip_id = y.equip_id		) 								AS equipDesc	");
    	query.append("		,(SELECT a.item_no  																	");
    	query.append("			FROM TAEQUIPMENT a																	");
    	query.append("			WHERE a.comp_no = y.comp_no															");
    	query.append("				AND a.equip_id = y.equip_id		) 								AS equipNo     	");
    	query.append("		   ,(SELECT description																	");
    	query.append("		   		FROM TAEQASMB																	");
    	query.append("		   		WHERE comp_no = z.comp_no														");
    	query.append("		   		AND eqasmb_id = z.eqasmb_id)								AS asmbDesc			");
    	query.append("		   ,z.check_point 													AS checkPoint		");
    	query.append("		   ,z.check_method 													AS checkMethod		");
    	query.append("		   ,z.fit_basis 													AS fitBasis			");
    	query.append("		   ,SFACODE_TO_DESC(z.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+loginUser.getLangId()+"') 							");
    	query.append("		   																	AS checkType		");
    	query.append("		   ,z.check_min||' / '||z.check_basis_val||' / '||z.check_max||							");
    	query.append("			CASE WHEN z.uom IS NULL THEN '' ELSE ' . ('||z.uom||')' END		AS checkUom			");
    	query.append("		   ,x.result_value 													AS resultValue		");
    	query.append("		   ,SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 		");
    	query.append("		   																AS pmPointRsltStatusDesc");
    	query.append("         ,(SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoNo             ");
    	query.append("         ,(SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS inspectWoDesc              ");
    	query.append("		   ,x.remark 														AS inspectRemark	");
    	query.append("		   ,x.pm_point_rep_status 											AS pmPointRepStatus	");
    	query.append("		   ,SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+loginUser.getLangId()+"') 			");
    	query.append("		   																AS pmPointRepStatusDesc	");
    	query.append("		   ,x.repair_desc 													AS repairDesc		");
    	query.append("		   ,x.repair_date 													AS repairDate		");
    	query.append("		   ,(SELECT a.description FROM TADEPT a 												");
    	query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id											");
    	query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 									");
    	query.append("		     				 	WHERE b.comp_no = x.comp_no 									");
    	query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept		");
    	query.append("		   ,x.repair_by 													AS repairBy			");
    	query.append("		   ,(SELECT emp_name																	");
    	query.append("		   		FROM TAEMP																		");
    	query.append("		   		WHERE comp_no = x.comp_no														");
    	query.append("		   		AND emp_id = x.repair_by) 									AS repairName		");
    	query.append("		   ,(SELECT a.param1 FROM TACDSYSD a 		                                            ");
    	query.append("		     WHERE a.list_type = 'BM_TYPE' 		                                                ");
    	query.append("		     AND a.key_no = 'BM_TYPE.'||(SELECT b.pm_type FROM TAWORKORDER b 					");
    	query.append("                             WHERE b.wongpoint_id = x.wongpoint_id))		AS param1			");
    	query.append("		   , x.woreq_id    													AS woreqId			");
    	query.append("		   , x.woplan_wkor_id    											AS woplanId			");
    	query.append("         ,(SELECT a.pminsdpoint_id															");
    	query.append("           FROM TAPMINSDPOINT	a																");
    	query.append("           WHERE a.comp_no = y.COMP_NO														");
    	query.append("           AND a.PMINSDLIST_ID = y.PMINSDLIST_ID												");
    	query.append("           AND a.pm_point_id = z.PM_POINT_ID)            					AS attachId	    	");
    	query.append("FROM TAWONGPOINT x, TAPMINSDLIST y, TAPMPOINT z												");
    	query.append("WHERE x.comp_no = y.comp_no																	");
    	query.append("AND y.comp_no = z.comp_no																		");
    	query.append("AND x.pm_point_id = z.pm_point_id																");
    	query.append("AND x.pminsdlist_id = y.pminsdlist_id															");
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
    	MaBdPointDetailDTO maBdPointDetailDTO = 
    			(MaBdPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaBdPointDetailDTO()));
    	
    	return maBdPointDetailDTO;
    }
    public String selectPmiType(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT pmi_type	FROM TAWONGPOINT	");
        query.append("WHERE 1=1								");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.append("AND wongpoint_id = '"+ maBdPointCommonDTO.getWoNgPointId() +"'");
    	
    	return QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointDetailDTO
     * @return
     */
    public int updateDetail(MaBdPointDetailDTO maBdPointDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAWONGPOINT SET		            ");
    	query.append("	     pm_rep_method_type   = 'DT',   ");
    	query.append("	     pm_point_rep_status  = ?,      ");
        query.append("       repair_desc          = ?,      ");
        query.append("       repair_date          = ?,      ");
        query.append("       repair_by            = ?       ");
    	query.append("WHERE  comp_no              = ?	    ");
    	query.append("  AND  wongpoint_id           = ?       ");
    	
    	Object[] objects = new Object[] {
    			maBdPointDetailDTO.getPmPointRepStatus(),
    			maBdPointDetailDTO.getRepairDesc(),
    			maBdPointDetailDTO.getRepairDate(),
    			maBdPointDetailDTO.getRepairBy(),
    			maBdPointDetailDTO.getCompNo(),
    			maBdPointDetailDTO.getWoNgPointId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteWongPoint(String pmiType, String listKeyId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAWONGPOINT x									");
    	query.append("WHERE 1=1													");
    	query.append("  AND x.comp_no = ?	    								");
    	query.append("  AND NVL(x.pm_rep_method_type,'-1') != 'DT'         	    ");	//조치하지 않은 항목
    	query.append("  AND NOT EXISTS ( SELECT 1								"); //작업서가 추가되지 않은 항목
    	query.append("                     FROM TAWONGPOINTRES a 				");
    	query.append("                    WHERE 1=1								");
    	query.append("                      AND a.comp_no = x.comp_no			");
    	query.append("                      AND a.wongpoint_id = x.wongpoint_id	");
    	query.append("                  ) 										");

    	switch(pmiType)
    	{
	    	case "INS" : 	//분해점검
	    		query.append("AND x.wkor_id 			= ?		");
	    		break;
	    	case "RINS" :	//정기점검
	    		query.append("AND x.pminslist_id 		= ?		");
	    		break;
	    	case "DINS" : 	//일상점검
	    		query.append("AND x.pminsdlist_id 		= ?		");
	    		break;
	    	case "CINS" : 	//파트체인지점검
	    		query.append("AND x.pminsdlist_id 		= ?		");
	    		break;
	    	case "PINS" :	//순회점검
	    		query.append("AND x.pmptrlrsltlist_id 	= ? 	");
	    		break;
    	}
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, listKeyId
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String getWongPointCount(String pmiType, String listKeyId, String pointKeyId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT COUNT(1)						");
    	query.append("FROM TAWONGPOINT a					");
    	query.append("WHERE 1=1								");
    	query.append("AND a.comp_no 				= ?		");
    	
    	switch(pmiType)
    	{
    	case "INS" : 	//분해점검
    		query.append("AND a.wkor_id 			= ?		");
    		query.append("AND a.wopoint_id 			= ?		");
    		break;
    	case "RINS" :	//정기점검
    		query.append("AND a.pminslist_id 		= ?		");
    		query.append("AND a.pminspoint_id 		= ?		");
    		break;
    	case "DINS" : 	//일상점검
    		query.append("AND a.pminsdlist_id 		= ?		");
    		query.append("AND a.pminsdpoint_id 		= ?		");
    		break;
    	case "CINS" : 	//파트체인지점검
    		query.append("AND a.pminsdlist_id 		= ?		");
    		query.append("AND a.pminsdpoint_id 		= ?		");
    		break;
    	case "PINS" :	//순회점검
    		query.append("AND a.pmptrlrsltlist_id 	= ? 	");
    		query.append("AND a.pmptrlrsltpoint_id 	= ?		");
    		break;
    	}
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, listKeyId
    			, pointKeyId
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    public int insertWongDetail(String pmiType, String listKeyId, String pmPointId, String pointKeyId, String actualDate, String rsltValue, String remark, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAWONGPOINT (									");
    	query.append("      comp_no		, wongpoint_id		, pm_point_id		");
    	query.append("    , pmi_type											");
    	
    	switch(pmiType)
    	{
    	case "INS" : 	//분해점검
    		query.append("    , wopoint_id			, wkor_id					");
    		break;
    	case "RINS" :	//정기점검
    		query.append("    , pminspoint_id 		, pminslist_id				");
    		break;
    	case "DINS" : 	//일상점검
    		query.append("    , pminsdpoint_id		, pminsdlist_id				");
    		break;
    	case "CINS" : 	//파트체인지점검
    		query.append("    , pminsdpoint_id		, pminsdlist_id				");
    		break;
    	case "PINS" :	//순회점검
    		query.append("    , pmptrlrsltpoint_id	, pmptrlrsltlist_id			");
    		break;
    	}
    	
    	query.append("    , pm_point_rslt_status	, actual_date				");
    	query.append("    , result_value  			, remark					");
    	query.append("    , pm_point_rep_status									");
    	query.append(") VALUES (												");
    	query.append("    	?   	, SQAWONGPOINT_ID.NEXTVAL 		, ?			");
    	query.append("    , ?													");
    	query.append("    , ? 		,?											");
    	query.append("    , ? 		,?											");
    	query.append("    , ? 		,?											");
    	query.append("    , ?													");
    	query.append(")															");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,pmPointId
    			,pmiType
    			,pointKeyId
    			,listKeyId
    			,"BD"
    			,actualDate
    			,rsltValue
    			,remark
    			,"BD"
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int updateWongDetail(String pmiType, String listKeyId, String pointKeyId, String actualDate, String rsltValue, String remark, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAWONGPOINT SET		");
    	query.append("    actual_date 		= ?		");
    	query.append("    , result_value 	= ?		");
    	query.append("    , remark 			= ?		");
    	query.append("WHERE comp_no 		= ?		");
    	
    	switch(pmiType)
    	{
    	case "INS" : 	//분해점검
    		query.append("AND wkor_id 				= ?		");
    		query.append("AND wopoint_id 			= ?		");
    		break;
    	case "RINS" :	//정기점검
    		query.append("AND pminslist_id 			= ?		");
    		query.append("AND pminspoint_id 		= ?		");
    		break;
    	case "DINS" : 	//일상점검
    		query.append("AND pminsdlist_id 		= ?		");
    		query.append("AND pminsdpoint_id 		= ?		");
    		break;
    	case "CINS" : 	//파트체인지점검
    		query.append("AND pminsdlist_id 		= ?		");
    		query.append("AND pminsdpoint_id 		= ?		");
    		break;
    	case "PINS" :	//순회점검
    		query.append("AND pmptrlrsltlist_id 	= ? 	");
    		query.append("AND pmptrlrsltpoint_id 	= ?		");
    		break;
    	}
    	
    	Object[] objects = new Object[] {
    			actualDate
    			, rsltValue
    			, remark
    			, user.getCompNo()
    			, listKeyId
    			, pointKeyId
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int updateNgDetail(MaBdPointDetailDTO maBdPointDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAWONGPOINT SET		        ");
    	query.append("	     pm_rep_method_type   = 'DT',   ");
    	query.append("	     pm_point_rep_status  = ?,      ");
        query.append("       repair_desc          = ?,      ");
        query.append("       repair_date          = ?,      ");
        query.append("       repair_by            = ?       ");
    	query.append("WHERE  comp_no              = ?	    ");
    	query.append("  AND  wongpoint_id         = ?       ");
    	
    	Object[] objects = new Object[] {
    			maBdPointDetailDTO.getPmPointRepStatus(),
    			maBdPointDetailDTO.getRepairDesc(),
    			CommonUtil.getRowDateToNum(maBdPointDetailDTO.getRepairDate()),
    			maBdPointDetailDTO.getRepairBy(),
    			maBdPointDetailDTO.getCompNo(),
    			maBdPointDetailDTO.getWoNgPointId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    public String findId(MaBdPointDetailDTO maBdPointDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT 					");
    	query.append("    x.woreq_id 	 		");
    	query.append("    ||','||  				");
    	query.append("    x.woplan_wkor_id   	");
    	query.append("FROM TAWONGPOINT x 		");
    	query.append("WHERE x.comp_no 	 = ? 	");
    	query.append("AND x.wongpoint_id = ?	");
    	
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			, maBdPointDetailDTO.getWoNgPointId()
    	};

        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
}