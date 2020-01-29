package dream.part.rpt.mawopthist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rpt.mawopthist.dao.MaWoPtHistListDAO;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;
import dream.part.rpt.mawopthist.dto.MaWoPtHistListDTO;
import dream.part.rpt.mawopthist.form.MaWoPtHistListForm;

/**
 * 부품사용이력 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maWoPtHistListDAOTarget"
 * @spring.txbn id="maWoPtHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoPtHistListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoPtHistListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistCommonDTO
     * @return List
     */
    public List findList(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                     		                               			");
        query.append("        ''                                        seqNo,        			");
        query.append("        ''                                        isDelCheck,    			");
        query.getDate("a.end_date", "endDate");
        query.append("      , a.wo_no                           		AS woNo         		");
        query.append("      , a.description                     		AS description  		");
        query.append("      ,(SELECT description                                				");
        query.append("        FROM TAPLANT                                    					");
        query.append("        WHERE comp_no = a.comp_no                        					");
        query.append("        AND plant = a.plant)           		AS plantDesc				");
        query.append("      , (SELECT SFAIDTODESC(a.dept_id, '', 'DEPT', a.comp_no) FROM DUAL) AS detpDesc 		");
        query.append("        ,(SELECT cc.full_desc            									");
        query.append("          FROM TAEQLOC cc    												");
        query.append("          WHERE d.comp_no = cc.comp_no            						");
        query.append("          AND e.eqloc_id = cc.eqloc_id) 				 AS eqlocDesc  		");
        query.append("        ,e.description                                 AS equipDesc    	");
        query.append("        ,e.item_no                                     AS equipNo    		");
        query.append("      ,(SELECT full_desc                                  		");
        query.append("          FROM TAEQASMB                                   		");
        query.append("         WHERE comp_no = a.comp_no                        		");
        query.append("           AND eqasmb_id = a.eqasmb_id)   AS eqAsmbDesc   		");
        query.append("      , c.part_no                         AS partNo       		");
        query.append("      , c.description                     AS partDesc     		");
        query.append("      , c.pt_size                         AS ptSize       		");
        query.append("      , c.MODEL                           AS MODEL        		");
        query.append("      , b.use_qty                         AS useQty       		");
        query.append("      , b.unit_price                      AS unitPrice    		");
        query.append("      , b.tot_price                       AS totPrice     		");
        query.append("      , c.part_id                         AS partId       		");
        query.append("      , b.wopart_id                       AS woPartId     		");
        query.append("FROM TAWORKORDER a 												");
        query.append("	INNER JOIN TAWOPARTS b              							");
        query.append("	ON a.comp_no = b.comp_no                                  		");
        query.append("	 AND a.wkor_id = b.wkor_id                                 		");
        query.append("		LEFT OUTER JOIN TAPARTS c                               	");
        query.append("		ON b.comp_no = c.comp_no                                	");
        query.append("		 AND b.part_id = c.part_id									");
        query.append("			INNER JOIN TAWOEQUIP d                                 	");
        query.append("			ON a.comp_no = d.comp_no                                ");
        query.append("			 AND a.wkor_id = d.wkor_id     							");
        query.append("				INNER JOIN TAEQUIPMENT e      						");
        query.append("				ON d.comp_no = e.comp_no                        	");
        query.append("				 AND d.equip_id = e.equip_id     					");
        query.append("WHERE 1=1                                                 	");
        query.append("  AND a.wo_status = 'C'                                   	");
        query.append(this.getWhere(maWoPtHistCommonDTO, loginUser));
        query.getOrderByQuery("a.end_date desc", maWoPtHistCommonDTO.getOrderBy(), maWoPtHistCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoPtHistCommonDTO.getIsLoadMaxCount(), maWoPtHistCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
//        query.getAndQuery("c.part_categ", "SPPT");
        query.getAndDateQuery("a.start_date", maWoPtHistCommonDTO.getFilterStartDate(), maWoPtHistCommonDTO.getFilterEndDate());
        query.getDeptLevelQuery("a.dept_id", maWoPtHistCommonDTO.getFilterDeptId(), 
                maWoPtHistCommonDTO.getFilterDeptDesc(), maWoPtHistCommonDTO.getFilterCompNo());
        if(!"".equals(maWoPtHistCommonDTO.getFilterEquipId())||!"".equals(maWoPtHistCommonDTO.getFilterEquipDesc())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description||item_no", 
                    maWoPtHistCommonDTO.getFilterEquipId(), maWoPtHistCommonDTO.getFilterEquipDesc());
            query.append("									))					");
        }
        query.getPartsQuery("b.part_id", maWoPtHistCommonDTO.getFilterPartId(), 
                maWoPtHistCommonDTO.getFilterPartDesc(), maWoPtHistCommonDTO.getFilterCompNo());
        query.getEqAssetQuery("(SELECT k.equip_id FROM TAWOEQUIP k WHERE k.wkor_id = a.wkor_id)", maWoPtHistCommonDTO.getFilterAssetId(), 
                maWoPtHistCommonDTO.getFilterAssetDesc(), maWoPtHistCommonDTO.getFilterCompNo());        
        //위치
        if(!"".equals(maWoPtHistCommonDTO.getFilterEqLocId())||!"".equals(maWoPtHistCommonDTO.getFilterEqLocDesc())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("eqloc_id", maWoPtHistCommonDTO.getFilterEqLocId(), maWoPtHistCommonDTO.getFilterEqLocDesc(), maWoPtHistCommonDTO.getFilterCompNo());
            query.append("									))					");
        }
        //종류
        if(!"".equals(maWoPtHistCommonDTO.getFilterEqCtgId())||!"".equals(maWoPtHistCommonDTO.getFilterEqCtgDesc())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqCtgLevelQuery("eqctg_id", maWoPtHistCommonDTO.getFilterEqCtgId(), maWoPtHistCommonDTO.getFilterEqCtgDesc(), maWoPtHistCommonDTO.getFilterCompNo());
            query.append("									))					");
        }
        //관리자(정)
        if(!"".equals(maWoPtHistCommonDTO.getFilterMainMngId())||!"".equals(maWoPtHistCommonDTO.getFilterMainMngName())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = main_mng_id AND aa.comp_no='"+maWoPtHistCommonDTO.getFilterCompNo()+"')", 
            		maWoPtHistCommonDTO.getFilterMainMngId(), maWoPtHistCommonDTO.getFilterMainMngName());
            query.append("									))					");
        }
        //관리자(부)
        if(!"".equals(maWoPtHistCommonDTO.getFilterSubMngId())||!"".equals(maWoPtHistCommonDTO.getFilterSubMngName())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("sub_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = sub_mng_id AND aa.comp_no='"+maWoPtHistCommonDTO.getFilterCompNo()+"')", 
            		maWoPtHistCommonDTO.getFilterSubMngId(), maWoPtHistCommonDTO.getFilterSubMngName());
          query.append("									))					");
        }
        //설비유형
        if(!"".equals(maWoPtHistCommonDTO.getFilterEqCtgTypeId())||!"".equals(maWoPtHistCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("eqctg_type", maWoPtHistCommonDTO.getFilterEqCtgTypeId(), maWoPtHistCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maWoPtHistCommonDTO.getFilterCompNo(),user.getLangId());
            query.append("									))					");
        }
        //법정설비
        if(!"".equals(maWoPtHistCommonDTO.getFilterIsLawEq())){
        	query.append("AND a.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getLikeQuery("is_law_eq", maWoPtHistCommonDTO.getFilterIsLawEq());
            query.append("									))					");
        }
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT aa.description FROM  TAPLANT aa WHERE aa.comp_no = a.comp_no AND aa.plant = a.plant )", 
                maWoPtHistCommonDTO.getFilterPlantId(), maWoPtHistCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int deleteParts(MaWoPtHistListDTO maWoPtHistListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAPARTS                                            ");
        query.append("WHERE  comp_no  = ?                                       ");
        query.append("  AND  part_id  = ?                                       ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                maWoPtHistListDTO.getPartId()
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 자재 거래처 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistListDTO
     * @param loginUser
     * @return
     */
    public int deletePtVendor(MaWoPtHistListDTO maWoPtHistListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAPTVENDOR                                         ");
        query.append("WHERE  comp_no  = ?                                       ");
        query.append("  AND  part_id  = ?                                       ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                maWoPtHistListDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 자재 첨부파일 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistListDTO
     * @param loginUser
     * @return
     */
    public int deleteObjDoc(MaWoPtHistListDTO maWoPtHistListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAOBJDOC                                           ");
        query.append("WHERE  comp_no   = ?                                      ");
        query.append("  AND  object_id = ?                                      ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                maWoPtHistListDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    

    /**
     * req hdr create
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqHdrPtBuy(MaWoPtHistListForm maWoPtHistListForm, String partId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	MaWoPtHistCommonDTO maWoPtHistCommonDTO = maWoPtHistListForm.getMaWoPtHistCommonDTO();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();

    	query.append("INSERT INTO TAPTPRLIST							");
    	query.append("	(comp_no,		ptprlist_id,	ptprlist_no,	");
    	query.append("	 description,	ptprlist_status,dept_id,		");
    	query.append("	 req_date,		user_id,		plant			");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 (SELECT description FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"'),				?,				?,				");
    	query.append("	 TO_CHAR(sysdate,'yyyymmdd'),?, ?				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maWoPtHistCommonDTO.getReqIdx(),
    			maWoPtHistCommonDTO.getReqIdx(),
    			"W",
    			user.getDeptId(),
    			user.getUserId(),
    			user.getPlant()
    	};
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

    /**
     * req dtl create
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqDtlPtBuy(MaWoPtHistListForm maWoPtHistListForm, String partId, User user, String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();
    	MaWoPtHistCommonDTO maWoPtHistCommonDTO = maWoPtHistListForm.getMaWoPtHistCommonDTO();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTPRITEM (								");
    	query.append("	currency,comp_no,		ptpritem_id,	ptprlist_id,");
    	query.append("	description,	part_id,		pt_size,			");
    	query.append("	rec_qty,		unit_price,     part_grade			");
    	query.append("	)	VALUES	(										");
    	query.append("	(select cdsysd_no from tacdsysd where list_Type='CURRENCY' and param1='Y'),	");
    	query.append("	?,				SQAPTPRITEM_ID.nextval,?,			");
    	query.append("	(SELECT description FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"'),	?,");
    	query.append("	(SELECT pt_size FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"'),");
    	query.append("	NVL((SELECT CASE WHEN								");
    	query.append("			NVL(y.safty_qty,0)							");
    	query.append("			- ABS(NVL(x.stock_qty,0))					");
    	query.append("			- ABS((SELECT NVL(SUM(use_qty),0) FROM TAWOPARTS		");
    	query.append("				WHERE wkor_id IN (SELECT wkor_id FROM TAWORKORDER WHERE comp_no='"+user.getCompNo()+"' AND wo_status='P')");
    	query.append("				AND part_id = x.part_id					");
    	query.append("			AND part_grade= 'A')) <=0 					");
    	query.append("		THEN 1											");
    	query.append("		ELSE  NVL(y.safty_qty,0)						");
    	query.append("			- ABS(NVL(x.stock_qty,0))					");
    	query.append("			- ABS((SELECT NVL(SUM(use_qty),0) FROM TAWOPARTS");
    	query.append("				WHERE wkor_id IN (SELECT wkor_id FROM TAWORKORDER WHERE comp_no='"+user.getCompNo()+"' AND wo_status='P')");
    	query.append("			AND part_id = x.part_id						");
    	query.append("		AND part_grade= 'A')) END						");
    	query.append("	FROM   TAPTSTOCK x,	TAPTSAFTYSTOCK y				");
    	query.append("	WHERE  x.comp_no = y.comp_no						");
    	query.append("	AND  x.wcode_id = y.wcode_id						");
    	query.append("	AND  x.part_id  = y.part_id							");
    	query.getStringEqualQuery("x.part_grade", "A");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.part_id", partId);
    	query.append("	),1),												");
    	query.append("	(SELECT NVL(x.unit_price,0) 						");
    	query.append("	FROM   TAPTSTOCK x									");
    	query.append("	WHERE 1=1											");
    	query.append("	AND ROWNUM = 1										");
    	query.getStringEqualQuery("x.part_grade", "A");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.part_id", partId);
    	query.append("	)													");
    	query.append("	    ,?                              				");
    	query.append(")														");
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maWoPtHistCommonDTO.getReqIdx(),
    			partId,
    			partGrade
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                              		");
        query.append("       COUNT(1)     						");
        query.append("FROM TAWORKORDER a 												");
        query.append("INNER JOIN TAWOPARTS b              								");
        query.append("ON a.comp_no = b.comp_no                                  		");
        query.append("AND a.wkor_id = b.wkor_id                                 		");
        query.append("LEFT OUTER JOIN TAPARTS c                               			");
        query.append("ON b.comp_no = c.comp_no                                			");
        query.append("AND b.part_id = c.part_id											");
        query.append("LEFT OUTER JOIN (SELECT 											");
        query.append("                  a.comp_no										");
        query.append("                  ,a.wkor_id										");
        query.append("                  ,b.eqloc_id										");
        query.append("                  ,a.eqctg_id										");
        query.append("                  ,a.description									");
        query.append("                  ,b.item_no										");
        query.append("                  FROM TAWOEQUIP a 								");
        query.append("                  INNER JOIN TAEQUIPMENT b						");
        query.append("                  ON a.comp_no = b.comp_no						");
        query.append("                  AND a.equip_id = b.equip_id						");
        query.append("                  ) d												");
        query.append("ON a.comp_no = d.comp_no											");
        query.append("AND a.wkor_id = d.wkor_id                             	  		");
        query.append("WHERE 1=1                                             	    	");
        query.append("  AND a.wo_status = 'C'                               	    	");
        query.append(this.getWhere(maWoPtHistCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}