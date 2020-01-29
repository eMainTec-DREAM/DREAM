package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;

import dream.asset.list.dao.MaEqMstrMoldDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;



/**
 * 설비마스터 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaEqMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maEqMstrMoldDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrMoldDetailDAOOraImpl  extends BaseJdbcDaoSupportOra implements MaEqMstrMoldDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return
     */
    public MaEqMstrDetailDTO findDetail(String equipId, String compNo, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT															");
        query.append("		x.equip_id									equipId,		");
        query.append("		x.item_no									itemNo,			");
        query.append("		x.description								equipDesc,		");
        query.append("		x.eqloc_id									eqLocId,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLocDesc,		");
        query.append("		x.eqctg_id									eqCtgId,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc,		");
        query.append("		x.maker										maker,			");
        query.append("		x.model_no									modelNo,		");
        query.append("		 x.eq_status								eqStatusId,		");
        query.append("		SFACODETODESC(x.eq_status,'EQ_STATUS','SYS','') eqStatusDesc,");
        query.append("		 x.plf_type									plfTypeId,		");
        query.append("		SFACODETODESC(x.plf_type,'PLF_TYPE','SYS','')	plfTypeDesc,");
        query.append("		 x.setup_date								setupDate,		");
        query.append("		 x.buy_date								    buyDAte,		");
        query.append("		 x.capacity									capacity,		");
        query.append("		 x.util_capa								utilCapa,		");
        query.append("		 x.buy_amt									buyAmt,			");
        query.append("		 x.is_law_eq								isLawEq,		");
        query.append("		 x.dept_id									deptId,			");
        query.append("		(SELECT description											");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,		");
        query.append("		 x.main_mng_id								mainMngId,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngName,	");
        query.append("		 x.sub_mng_id								subMngId,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngName,		");
        query.append("		 x.remark									remark,			");
        query.append("		 x.old_eq_no								oldEqNo,		");
        query.append("		 x.ord_no									ordNo			");
        query.append("		,x.is_last_version							isLastVersion 	");
        query.append("		,x.revisionhist_id							revisionHistId	");
        query.append("		,x.revision_status							revisionStatusId");
        query.append("      ,x.lnwrklist_id                             lnWrkListId     ");
        query.append("      ,(SELECT aa.description                                     ");
        query.append("      FROM TALNWRKLIST aa                                         ");
        query.append("      WHERE x.comp_no = aa.comp_no                                ");
        query.append("      AND x.lnwrklist_id = aa.lnwrklist_id)       lnWrkListDesc   ");
        query.append("      ,x.currency									currencyId		");
        query.append("      ,SFACODE_TO_DESC(x.currency,'CURRENCY','USR',x.comp_no,'"+user.getLangId()+"')    currencyDesc	");
        query.append("      ,x.usage_dept                               usageDeptId     ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("         FROM TADEPT a                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id = x.usage_dept)           usageDeptDesc	");
        query.append("		,x.tag_no 									tagNo			");
        query.append("		,x.wkctr_id 								wkctrId			");
        query.append("		,(SELECT y.description 										");
        query.append("			FROM TAWKCTR y 											");
        query.append("			WHERE y.comp_no = x.comp_no 							");
        query.append("			AND y.wkctr_id = x.wkctr_id) 			wkctrDesc		");
        query.append("		,x.plant 									plant			");
        query.append("  	,(SELECT a.description 										");
        query.append("			FROM TAPLANT a 											");
        query.append("			WHERE a.comp_no = x.comp_no 							");
        query.append("			AND a.plant = x.plant ) 				plantDesc		");
        query.append("FROM   TAEQUIPMENT x												");
        query.append("WHERE  x.equip_id = '"+equipId+"'									");
        query.append("  AND  x.comp_no  = '"+compNo+"'									");
    
        MaEqMstrDetailDTO maEqMstrDetailDTO = 
        		(MaEqMstrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrDetailDTO()));
        
        return maEqMstrDetailDTO;
    }
    
    
    public MaEqMstrMoldDetailDTO findMoldDetail(String equipId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                         ");
        query.append("     storage           as storage              ");
        query.append("    ,setcnt            as setcnt               ");
        query.append("    ,output            as output               ");
        query.append("    ,ownership         as ownership            ");
        query.append("    ,is_disuse         as isdisuse             ");
        query.append("    ,disuse_date       as disusedate           ");
        query.append("    ,disuse_amt        as disuseamt            ");
        query.append("    ,disuse_vendor     as disusevendor         ");
        query.append("    ,use_period        as useperiod            ");
        query.append("    ,cavity            as cavity               ");
        query.append("    ,stepno            as stepno               ");
        query.append("    ,eqmold_atype      as eqMoldAtype          ");
        query.append("    ,SFACODE_TO_DESC(eqmold_atype,'EQMOLD_ATYPE','USR',comp_no,'"+user.getLangId()+"') as eqMoldAtypeDesc ");
        query.append("    ,eqmold_btype      as eqMoldBtype          ");
        query.append("    ,SFACODE_TO_DESC(eqmold_btype,'EQMOLD_BTYPE','USR',comp_no,'"+user.getLangId()+"') as eqMoldBtypeDesc ");
        query.append("    ,eqmold_ctype      as eqMoldCtype          ");
        query.append("    ,SFACODE_TO_DESC(eqmold_ctype,'EQMOLD_CTYPE','USR',comp_no,'"+user.getLangId()+"') as eqMoldCtypeDesc ");
        query.append("    ,eqstrloc_no       as eqStrLocNo           ");
        query.append("from TAEQMOLD a                                ");
        query.append("where a.comp_no = '"+user.getCompNo()+"'                 ");
        query.append("    and a.equip_id = '"+equipId+"'	         ");
    
        MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = 
        		(MaEqMstrMoldDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrMoldDetailDTO()));
        
        return maEqMstrMoldDetailDTO;
    }
    
    
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQUIPMENT						");
    	query.append("	(comp_no,		equip_id,		item_no,	");
    	query.append("	 description,	eqloc_id,		eqctg_id,	");
    	query.append("	 eq_status,		dept_id,		main_mng_id,");
    	query.append("	 sub_mng_id,	setup_date,		buy_amt,	");
    	query.append("	 plf_type,		maker,			model_no,	");
    	query.append("	 capacity,		util_capa,		is_law_eq,	");
    	query.append("	 ord_no,		remark,         old_eq_no,	");
    	query.append("	 EQCTG_TYPE,	lnwrklist_id,	currency,   ");
    	query.append("	 usage_dept,	tag_no,			wkctr_id,	");
    	query.append("	 buy_date, 		plant                      	");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,              ?,			");
    	query.append("	 ?,				?,				?,          ");
    	query.append("	 ?,				?,				?,          ");
    	query.append("	 ?,             ?                  	        ");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getCompNo(),
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMstrDetailDTO.getItemNo(),
    			maEqMstrDetailDTO.getEquipDesc(),
    			maEqMstrDetailDTO.getEqLocId(),
    			maEqMstrDetailDTO.getEqCtgId(),
    			maEqMstrDetailDTO.getEqStatusId(),
    			maEqMstrDetailDTO.getDeptId(),
    			maEqMstrDetailDTO.getMainMngId(),
    			maEqMstrDetailDTO.getSubMngId(),
    			maEqMstrDetailDTO.getSetupDate(),
    			maEqMstrDetailDTO.getBuyAmt(),
    			maEqMstrDetailDTO.getPlfTypeId(),
    			maEqMstrDetailDTO.getMaker(),
    			maEqMstrDetailDTO.getModelNo(),
    			maEqMstrDetailDTO.getCapacity(),
    			maEqMstrDetailDTO.getUtilCapa(),
    			maEqMstrDetailDTO.getIsLawEq(),
    			maEqMstrDetailDTO.getOrdNo(),
    			maEqMstrDetailDTO.getRemark(),
    			maEqMstrDetailDTO.getOldEqNo(),
    			"MD",
    			maEqMstrDetailDTO.getLnWrkListId(),
    			maEqMstrDetailDTO.getCurrencyId(),
    			maEqMstrDetailDTO.getUsageDeptId(),
    			maEqMstrDetailDTO.getTagNo(),
    			maEqMstrDetailDTO.getWkctrId(),
    			maEqMstrDetailDTO.getBuyDate(),
    			maEqMstrDetailDTO.getPlant()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    public int insertMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQMOLD   						");
    	query.append("	(comp_no,		equip_id,		storage,	");
    	query.append("	 setcnt,	    output,		    ownership,	");
    	query.append("	 is_disuse,		disuse_date,	disuse_amt, ");
    	query.append("	 disuse_vendor,	use_period,     cavity,     ");
    	query.append("	 eqmold_atype,  eqmold_btype,   eqmold_ctype,");
    	query.append("	 stepno,        eqstrloc_no                 ");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,              ?,			");
    	query.append("	 ?,				?,              ?,			");
    	query.append("	 ?,             ?                   		");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getCompNo()
    			,maEqMstrDetailDTO.getEquipId()
    			,maEqMstrMoldDetailDTO.getStorage()
    			,maEqMstrMoldDetailDTO.getSetCnt()
    			,maEqMstrMoldDetailDTO.getOutPut()
    			,maEqMstrMoldDetailDTO.getOwnerShip()
    			,maEqMstrMoldDetailDTO.getIsDisUse()
    			,maEqMstrMoldDetailDTO.getDisUseDate()
    			,maEqMstrMoldDetailDTO.getDisUseAmt()
    			,maEqMstrMoldDetailDTO.getDisUseVendor()
    			,maEqMstrMoldDetailDTO.getUsePeriod()
    			,maEqMstrMoldDetailDTO.getCavity()
    			,maEqMstrMoldDetailDTO.getEqMoldAtype()
    			,maEqMstrMoldDetailDTO.getEqMoldBtype()
    			,maEqMstrMoldDetailDTO.getEqMoldCtype()
    			,maEqMstrMoldDetailDTO.getStepNo()
    			,maEqMstrMoldDetailDTO.getEqStrLocNo()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    
    /**
     * 설비변경이력 insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQALTHIST								");
    	query.append("	(comp_no,		eqalthist_id,		upd_date,		");
    	query.append("	 upd_by,		equip_id,			item_no,		");
    	query.append("	 description,	eqloc_id,			eqctg_id,		");
    	query.append("	 eq_status,		dept_id,			main_mng_id,	");
    	query.append("	 sub_mng_id,	is_law_eq,			remark			");
    	query.append("	)	VALUES											");
    	query.append("	(?,				SQAEQALTHIST_ID.nextval,TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS'),	");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?			");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getCompNo(),
    			maEqMstrDetailDTO.getEnterBy(),
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMstrDetailDTO.getItemNo(),
    			maEqMstrDetailDTO.getEquipDesc(),
    			maEqMstrDetailDTO.getEqLocId(),
    			maEqMstrDetailDTO.getEqCtgId(),
    			maEqMstrDetailDTO.getEqStatusId(),
    			maEqMstrDetailDTO.getDeptId(),
    			maEqMstrDetailDTO.getMainMngId(),
    			maEqMstrDetailDTO.getSubMngId(),
    			maEqMstrDetailDTO.getIsLawEq(),
    			maEqMstrDetailDTO.getRemark()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 저장시 설비부위 추가
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertEqAsmb(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("DELETE FROM TAEQASMB			");
    	query.append("WHERE EQCTG_ID != ?			");
    	query.append("  AND EQUIP_ID  = ?			");
    	query.append("  AND COMP_NO   = ?			");
    	query.append("  AND EQCTG_ID IS NOT NULL	");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEqCtgId(),
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMstrDetailDTO.getCompNo()
    	};
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	if(rtnValue>0){
    		query = new QueryBuffer();
        	query.append("INSERT INTO TAEQASMB								");
        	query.append("	(comp_no,		eqasmb_id,		equip_id,		");
        	query.append("	 eq_ctg_asmb_id,eqctg_id,		is_eqtype_asmb,	");
        	query.append("	 description,	is_use,			ord_no			");
        	query.append("	) SELECT 										");
        	query.append("	comp_no,		SQAEQASMB_ID.nextval, ?,		");
        	query.append("	eq_ctg_asmb_id,	eqctg_id,		'Y',			");
        	query.append("	description,	is_use,		ord_no				");
        	query.append("	FROM TAEQCTGASMB								");
        	query.append("	WHERE comp_no  = ?								");
        	query.append("	  AND eqctg_id = ?								");
        	
        	Object[] objects1 = new Object[] {
        			maEqMstrDetailDTO.getEquipId(),
        			maEqMstrDetailDTO.getCompNo(),
        			maEqMstrDetailDTO.getEqCtgId()
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET						");
    	query.append("	description		= ?, eqloc_id		= ?,	");
    	query.append("	eqctg_id		= ?, maker			= ?,	");
    	query.append("	model_no		= ?, capacity		= ?,	");
    	query.append("	setup_date		= ?, plf_type		= ?,	");
    	query.append("	util_capa		= ?, dept_id		= ?,	");
    	query.append("	buy_amt			= ?, is_law_eq		= ?,	");
    	query.append("	main_mng_id		= ?, sub_mng_id		= ?,	");
    	query.append("	ord_no			= ?, remark			= ?,	");
    	query.append("	eq_status		= ?, old_eq_no      = ?,	");
    	query.append("	lnwrklist_id	= ?, currency		= ?,	");
    	query.append("	usage_dept		= ?, tag_no			= ?,	");
    	query.append("	wkctr_id		= ?, buy_date       = ?,	");
    	query.append("	plant		    = ?							");
    	query.append("WHERE equip_id 	= ?							");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEquipDesc()
    			,maEqMstrDetailDTO.getEqLocId()
    			,maEqMstrDetailDTO.getEqCtgId()
    			,maEqMstrDetailDTO.getMaker()
    			,maEqMstrDetailDTO.getModelNo()
    			,maEqMstrDetailDTO.getCapacity()
    			,maEqMstrDetailDTO.getSetupDate()
    			,maEqMstrDetailDTO.getPlfTypeId()
    			,maEqMstrDetailDTO.getUtilCapa()
    			,maEqMstrDetailDTO.getDeptId()
    			,maEqMstrDetailDTO.getBuyAmt()
    			,maEqMstrDetailDTO.getIsLawEq()
    			,maEqMstrDetailDTO.getMainMngId()
    			,maEqMstrDetailDTO.getSubMngId()
    			,maEqMstrDetailDTO.getOrdNo()
    			,maEqMstrDetailDTO.getRemark()
    			,maEqMstrDetailDTO.getEqStatusId()
    			,maEqMstrDetailDTO.getOldEqNo()
    			,maEqMstrDetailDTO.getLnWrkListId()
    			,maEqMstrDetailDTO.getCurrencyId()
    			,maEqMstrDetailDTO.getUsageDeptId()
    			,maEqMstrDetailDTO.getTagNo()
    			,maEqMstrDetailDTO.getWkctrId()
    			,maEqMstrDetailDTO.getBuyDate()
    			,maEqMstrDetailDTO.getPlant()
    			,maEqMstrDetailDTO.getEquipId()
    			,maEqMstrDetailDTO.getCompNo()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQMOLD SET						");
    	query.append("	storage		    = ?                  	");
    	query.append("	,setcnt 		= ?	                    ");
    	query.append("	,output 		= ?	                    ");
    	query.append("	,ownership		= ?	                    ");
    	query.append("	,is_disuse		= ?	                    ");
    	query.append("	,disuse_date	= ?	                    ");
    	query.append("	,disuse_amt		= ?	                    ");
    	query.append("	,disuse_vendor	= ?	                    ");
    	query.append("	,use_period		= ?		    			");
    	query.append("	,cavity 		= ?		    			");
    	query.append("	,eqmold_atype 	= ?		    			");
    	query.append("	,eqmold_btype 	= ?		    			");
    	query.append("	,eqmold_ctype 	= ?		    			");
    	query.append("	,stepno     	= ?		    			");
    	query.append("	,eqstrloc_no    = ?		    			");
    	query.append("WHERE equip_id 	= ?			 			");
    	query.append("  AND comp_no		= ?						");
    	
    	Object[] objects = new Object[] {
    			maEqMstrMoldDetailDTO.getStorage()
    			,maEqMstrMoldDetailDTO.getSetCnt()
    			,maEqMstrMoldDetailDTO.getOutPut()
    			,maEqMstrMoldDetailDTO.getOwnerShip()
    			,maEqMstrMoldDetailDTO.getIsDisUse()
    			,maEqMstrMoldDetailDTO.getDisUseDate()
    			,maEqMstrMoldDetailDTO.getDisUseAmt()
    			,maEqMstrMoldDetailDTO.getDisUseVendor()
    			,maEqMstrMoldDetailDTO.getUsePeriod()
    			,maEqMstrMoldDetailDTO.getCavity()
    			,maEqMstrMoldDetailDTO.getEqMoldAtype()
    			,maEqMstrMoldDetailDTO.getEqMoldBtype()
    			,maEqMstrMoldDetailDTO.getEqMoldCtype()
    			,maEqMstrMoldDetailDTO.getStepNo()
    			,maEqMstrMoldDetailDTO.getEqStrLocNo()
    			,maEqMstrDetailDTO.getEquipId()
    			,maEqMstrDetailDTO.getCompNo()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 예방점검 시행여부 N으로 변경
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int updatePmActive(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPMLST SET							");
    	query.append("	is_active		= 'N'						");
    	query.append("WHERE equip_id 	= ?							");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMstrDetailDTO.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * 오늘날짜 이후 wo미발행 스케쥴 삭제
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int deletePmSched(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPMSCHED x								");
    	query.append("WHERE  x.plan_date > TO_CHAR(SYSDATE,'yyyymmdd')		");
    	query.append("  AND  x.pm_id IN (SELECT pm_id						");
    	query.append("					   FROM TAPMLST						");
    	query.append("					  WHERE equip_id = ?				");
    	query.append("					    AND comp_no = x.comp_no)		");
    	query.append("  AND  x.comp_no	= ?									");
    	query.append("  AND  x.wkor_id	IS NULL								");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMstrDetailDTO.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportEquipDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();
		String lang = maEqMstrDetailDTO.getUserLang();
        query.append("SELECT																										");
        query.append("		x.equip_id									equipId,													");
        query.append("		x.item_no									itemNo,														");
        query.append("		x.description								equipDesc,													");
        query.append("		x.maker										maker,														");
        query.append("		x.model_no									model,														");
        query.append("		(SELECT (SELECT description 																			");
        query.append("					FROM TAASSET																				");
        query.append("					WHERE asset_id = a.asset_id																	");
        query.append("					  AND comp_no = a.comp_no)																	");
        query.append("			FROM TAEQASSET a																					");
        query.append("			WHERE a.comp_no = x.comp_no																			");
        query.append("			  AND a.equip_id = x.equip_id																		");
        query.append("			  AND ROWNUM = 1) assetNo,																			");
        query.append("		SFACODETODESC(x.eq_status,'EQ_STATUS','SYS','') eqStatusDesc,											");
        query.append("		TO_CHAR( TO_DATE(x.setup_date,'yyyymmdd'),'YYYY-MM-DD') setupDate,										");
        query.append("		 x.capacity									capacity,													");
        query.append("		 x.util_capa								utilCapa,													");
        query.append("		 x.buy_amt									buyAmt,														");
        query.append("		(SELECT description																						");
        query.append("		   FROM TADEPT																							");
        query.append("		  WHERE comp_no = x.comp_no																				");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,													");
        query.append("		TO_CHAR(sysdate,'yyyy-mm-dd HH24:mi') TODAY,															");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqHistCard') eqHistCard,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo') equipNo,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqMainHist') eqMainHist,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='deptDesc') eqDeptDesc,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName') equipName,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='setupDate') eqSetupDate,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='assetNo') eqAssetNo,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='setupAmt') setupAmt,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='maker') eqMaker,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='capacity') eqCapacity,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='model') eqModel,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='utilCapa') eqUtilCapa,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqPtSizeList') eqPtSizeList,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='separation') separation,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize') partNameSize,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seller') eqPartSellerDesc,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark') eqPartRemark,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repChgList') repChgList,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='yyyymmdd') repYyyymmdd,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repairPart') repairPart,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repChgDesc') repChgDesc,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repBy') repBy,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark') repRemark,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='app') repApp,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqInsertOil') eqInsertOil,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertOilSize') insertOilSize,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='separation') oilseparation,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='oilGreaseSize') oilGreaseSize,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seller') oilSeller,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark') oilRemark,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='refuelingCondition') refuelingCondition,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='yyyymmdd') oilYyyymmdd,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='oilPart') oilPart,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='oilDesc') oilDesc,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark') oil2Remark,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='oilBy') oilBy,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='app') oilApp								");
        query.append("FROM   TAEQUIPMENT x																							");
        query.append("WHERE  x.equip_id = '"+maEqMstrDetailDTO.getEquipId()+"'														");
        query.append("  AND  x.comp_no  = '"+maEqMstrDetailDTO.getCompNo()+"'														");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    
    /**
     * 리포트 설비부품
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT (SELECT description								");
        query.append("			FROM TAEQASMB 									");
        query.append("			WHERE equip_id = x.equip_id						");
        query.append("			  AND comp_no = x.comp_no						");
        query.append("			  AND eqasmb_id= x.eqasmb_id) EQPARTTYPE,		");
        query.append("		y.description||'('||y.pt_size||')'  EQPARTDESC,		");
        query.append("		y.seller EQPARTSELLER								");
        query.append("FROM TAEQPART x, TAPARTS y								");
        query.append("WHERE x.comp_no = y.comp_no								");
        query.append("  AND x.part_id = y.part_id								");
        query.append("  AND x.equip_id="+maEqMstrDetailDTO.getEquipId()+"		");
        query.append("  AND x.comp_no='"+maEqMstrDetailDTO.getCompNo()+"'		");
        query.append("order by x.eqasmb_id										");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    
    /**
     * 리포트 수리 및 교체내역
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoRepDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd') repEndDate,");
        query.append("		(SELECT												");
        query.append("			(SELECT description								");
        query.append("				FROM TAEQASMB								");
        query.append("				WHERE eqasmb_id=a.eqasmb_id					");
        query.append("				  AND comp_no = x.comp_no)					");
        query.append("			FROM TAWOFAIL a									");
        query.append("			WHERE comp_no = x.comp_no						");
        query.append("			  AND wkor_id = x.wkor_id) repAsmb,				");
        query.append("		x.description REPDESC,								");
        query.append("		(SELECT emp_name									");
        query.append("		FROM TAEMP											");
        query.append("		WHERE comp_no=x.comp_no								");
        query.append("		  AND emp_id = x.emp_id) repEmpName					");
        query.append("FROM TAWORKORDER x										");
        query.append("WHERE x.pm_type IN ('RPR', 'RPL')							");
        query.append("  AND x.equip_id="+maEqMstrDetailDTO.getEquipId()+"		");
        query.append("  AND x.comp_no='"+maEqMstrDetailDTO.getCompNo()+"'		");
        query.append("order by x.end_date										");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    
    /**
     * 리포트 주유현황
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoOilDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd') oilEndDate,");
        query.append("		(SELECT												");
        query.append("			(SELECT description								");
        query.append("				FROM TAEQASMB								");
        query.append("				WHERE eqasmb_id=a.eqasmb_id					");
        query.append("				  AND comp_no = x.comp_no)					");
        query.append("			FROM TAWOFAIL a									");
        query.append("			WHERE comp_no = x.comp_no						");
        query.append("			  AND wkor_id = x.wkor_id) oilAsmb,				");
        query.append("		x.description oilDesc,								");
        query.append("		(SELECT emp_name									");
        query.append("		FROM TAEMP											");
        query.append("		WHERE comp_no=x.comp_no								");
        query.append("		  AND emp_id = x.emp_id) oilEmpName					");
        query.append("FROM TAWORKORDER x										");
        query.append("WHERE x.pm_type IN ('OIL')							");
        query.append("  AND x.equip_id="+maEqMstrDetailDTO.getEquipId()+"		");
        query.append("  AND x.comp_no='"+maEqMstrDetailDTO.getCompNo()+"'		");
        query.append("order by x.end_date										");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    
    public List findReportWoImgDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

		String image1= "C:\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ottogi\\imgSlide\\img_list.jpg";
		image1 = image1.replaceAll("\\\\", "/");
		String image2= "C:\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ottogi\\imgSlide\\img_list.jpg";
		image2 = image2.replaceAll("\\\\", "/");
		
        query.append("SELECT '"+image1+"' \"C:/aa.jpg\",					");
        query.append("		'"+image2+"' \"image2.jpg\"						");
        query.append("FROM TAWORKORDER x									");
        query.append("where rownum=1										");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    
    public void SP_IF_UPD_TXEQUIPMENT(String equipId, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXEQUIPMENT('"+loginUser.getCompNo()+"',"+equipId+", '"+loginUser.getUserNo()+"');     ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public int updateIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQUIPMENT SET		");
    	query.append("	is_last_version = ?			");
    	query.append("	,revision_status = ?		");
    	query.append("WHERE 1=1						");
    	query.append("AND comp_no = ?				");
    	query.append("AND revisionhist_id = ?		");
    	
    	Object[] objects = new Object[] {
    			"Y"
    			,"C"
    			,user.getCompNo()
    			,maEqMstrDetailDTO.getRevisionhistId()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}
	
	public int updateBeforeIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQUIPMENT SET		");
    	query.append("	is_last_version = ?			");
    	query.append("	,revision_status = ?		");
    	query.append("WHERE 1=1						");
    	query.append("AND comp_no = ?				");
    	query.append("AND item_no = ?				");
    	
    	Object[] objects = new Object[] {
    			"N"
    			,"C"
    			,user.getCompNo()
    			,maEqMstrDetailDTO.getItemNo()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}
    
	public int updateRevisionHist(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAREVISIONHIST SET			");
    	query.append("	revision_status 		= ?		");
    	query.append("	,revision_step_status 	= ?		");
    	query.append("WHERE 1=1							");
    	query.append("AND comp_no = ?					");
    	query.append("AND revisionhist_id	 = ?		");
    	
    	Object[] objects = new Object[] {
    			"C"
    			,"CMP"
    			,user.getCompNo()
    			,maEqMstrDetailDTO.getRevisionhistId()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}

	public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, String revisionHistId,String revisionStatus, User loginUser)
	{
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQUIPMENT									");
    	query.append("	(comp_no			,equip_id			,item_no		");
    	query.append("	 ,description		,eqloc_id			,eqctg_id		");
    	query.append("	 ,eq_status			,dept_id			,main_mng_id	");
    	query.append("	 ,sub_mng_id		,setup_date			,buy_amt		");
    	query.append("	 ,plf_type			,maker				,model_no		");
    	query.append("	 ,capacity			,util_capa			,is_law_eq		");
    	query.append("	 ,ord_no			,remark         	,old_eq_no		");
    	query.append("	 ,eqctg_type		,lnwrklist_id		,currency   	");
    	query.append("	 ,as_vendor			,as_name			,as_phone		");
    	query.append("	 ,serial_no			,eq_grade       	,plant			");
    	query.append("	 ,buy_date			,p_equip_id			,quantity		");
    	query.append("	 ,disused_date 		,tag_no				,run_date		");
    	query.append("	 ,pmi_action_type 	,prod_shape 		,eqstrloc_no 	");
    	query.append("	 ,supplier 			,country_maker  	,ctctr_id 		");
    	query.append("	 ,eq_spec			,usage_emp			,weight	 		");
    	query.append("	 ,eq_size			,upd_by				,upd_date		");
    	query.append("   ,guaranty_date		,cre_by				,cre_date		");
    	query.append("	 ,usage_dept		,revisionhist_id	,revision_status ");
    	query.append("	 ,is_last_version	,is_deleted							");
    	query.append("	)														");
    	query.append("SELECT 													");
    	query.append("	 comp_no			,?					,?				");
    	query.append("	 ,description||'-Copyed'	,eqloc_id	,eqctg_id		");
    	query.append("	 ,eq_status			,dept_id			,main_mng_id	");
    	query.append("	 ,sub_mng_id		,setup_date			,buy_amt		");
    	query.append("	 ,plf_type			,maker				,model_no		");
    	query.append("	 ,capacity			,util_capa			,is_law_eq		");
    	query.append("	 ,ord_no			,remark         	,old_eq_no		");
    	query.append("	 ,eqctg_type		,lnwrklist_id		,currency   	");
    	query.append("	 ,as_vendor			,as_name			,as_phone		");
    	query.append("	 ,serial_no			,eq_grade       	,plant			");
    	query.append("	 ,buy_date			,p_equip_id			,quantity		");
    	query.append("	 ,disused_date 		,tag_no				,run_date		");
    	query.append("	 ,pmi_action_type 	,prod_shape 		,eqstrloc_no 	");
    	query.append("	 ,supplier 			,country_maker  	,ctctr_id 		");
    	query.append("	 ,eq_spec			,usage_emp			,weight	 		");
    	query.append("	 ,eq_size			,?			,TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')	");
    	query.append("   ,guaranty_date 	,?			,TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')	");
    	query.append("	 ,usage_dept		,?					,?			 	");
    	query.append("	 ,?					,'N'								");
        query.append("FROM TAEQUIPMENT											");
        query.append("WHERE comp_no		= ?										");
        query.append(" AND  equip_id	= ?										");
    
    	
    	Object[] objects = new Object[] {
    			  maEqMstrDetailDTO.getEquipId()
    			, maEqMstrDetailDTO.getItemNo()
    			, loginUser.getUserId()
    			, loginUser.getUserId()
      			, revisionHistId
      			, revisionStatus
      			, "N".equals(MwareConfig.getIsUseAssetRevision())?"Y":"N"
      			, loginUser.getCompNo()
      			, maEqMstrDetailDTO.getOldEquipId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}
	
    public int copyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser)
    {
		QueryBuffer query = new QueryBuffer();

		query.append("INSERT INTO TAEQMOLD							");
		query.append("	( comp_no		,equip_id		,storage	");
    	query.append("	 ,setcnt	    ,output		    ,ownership	");
    	query.append("	 ,is_disuse		,disuse_date	,disuse_amt ");
    	query.append("	 ,disuse_vendor	,use_period     ,cavity    	");
		query.append("	 ,eqmold_atype	,eqmold_btype				");
		query.append("	)											");
		query.append("SELECT 										");
    	query.append("	  comp_no		,?				,storage	");
    	query.append("	 ,setcnt	    ,output		    ,ownership	");
    	query.append("	 ,is_disuse		,disuse_date	,disuse_amt ");
    	query.append("	 ,disuse_vendor	,use_period     ,cavity    	");
		query.append("	 ,eqmold_atype	,eqmold_btype				");
        query.append("FROM TAEQMOLD									");
        query.append("WHERE comp_no		= ?							");
        query.append(" AND  equip_id	= ?							");
		
		
		Object[] objects = new Object[] {
				  maEqMstrDetailDTO.getEquipId()
				, loginUser.getCompNo()
				, maEqMstrDetailDTO.getOldEquipId()
		};
		
		return this.getJdbcTemplate().update(query.toString(), objects);
    }

	public int insertCopyEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) 
	{
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQALTHIST								");
    	query.append("	(comp_no,		eqalthist_id,		upd_date,		");
    	query.append("	 upd_by,		equip_id,			item_no,		");
    	query.append("	 description,	eqloc_id,			eqctg_id,		");
    	query.append("	 eq_status,		dept_id,			main_mng_id,	");
    	query.append("	 sub_mng_id,	is_law_eq,			remark			");
    	query.append("	)	VALUES											");
    	query.append("	(?,				SQAEQALTHIST_ID.nextval,TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS'),	");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?			");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			  loginUser.getCompNo()
    			, loginUser.getUserId()
    			, maEqMstrDetailDTO.getEquipId()
    			, maEqMstrDetailDTO.getItemNo()
    			, maEqMstrDetailDTO.getEquipDesc()
    			, maEqMstrDetailDTO.getEqLocId()
    			, maEqMstrDetailDTO.getEqCtgId()
    			, maEqMstrDetailDTO.getEqStatusId()
    			, maEqMstrDetailDTO.getDeptId()
    			, maEqMstrDetailDTO.getMainMngId()
    			, maEqMstrDetailDTO.getSubMngId()
    			, maEqMstrDetailDTO.getIsLawEq()
    			, maEqMstrDetailDTO.getRemark()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}

	public String getNextSequence(String seqName)
    {
    	return super.getNextSequence(seqName);
    }
	
	
}