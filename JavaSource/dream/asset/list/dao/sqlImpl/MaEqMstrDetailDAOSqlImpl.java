package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.MaEqMstrDetailDAO;
import dream.asset.list.dto.AssetListITDetailDTO;
import dream.asset.list.dto.MaEqBuildMstrDetailDTO;
import dream.asset.list.dto.MaEqDeviceMstrDetailDTO;
import dream.asset.list.dto.MaEqMoldMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqToolMstrDetailDTO;

/**
 * 설비마스터 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaEqMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maEqMstrDetailDAOTarget"
 * @spring.txbn id="maEqMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrDetailDAO
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
    public MaEqMstrDetailDTO findDetail(String equipId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT															");
        query.append("		x.equip_id									equipId			");
        query.append("		,x.item_no									itemNo			");
        query.append("		,x.description								equipDesc		");
        query.append("		,x.eqloc_id									eqLocId			");
        query.append("		,(SELECT a.eqloc_no											");
        query.append("		   FROM TAEQLOC	a											");
        query.append("		  WHERE a.comp_no = x.comp_no								");
        query.append("		    AND a.eqloc_id = x.eqloc_id)			eqLocNo			");
        query.append("		,(SELECT a.full_desc										");
        query.append("		   FROM TAEQLOC	a											");
        query.append("		  WHERE a.comp_no = x.comp_no								");
        query.append("		    AND a.eqloc_id = x.eqloc_id)			eqLocDesc		");
        query.append("		,x.eqctg_id									eqCtgId			");
        query.append("		,(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc		");
        query.append("		,x.maker									maker			");
        query.append("		,x.model_no									modelNo			");
        query.append("	    ,x.eq_status								eqStatusId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"') eqStatusDesc");
        query.append("		,x.eqctg_type								eqCtgTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"') eqCtgTypeDesc");
        query.append("		,x.plf_type									plfTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')	plfTypeDesc");
        query.append("		,x.setup_date								setupDate		");
        query.append("		,x.capacity									capacity		");
        query.append("		,x.util_capa								utilCapa		");
        query.append("		,x.buy_amt									buyAmt			");
        query.append("      ,x.buy_date                                 buyDate         ");
        query.append("      ,x.disused_date                             disusedDate     ");
        query.append("		,x.is_law_eq								isLawEq			");
        query.append("		,x.dept_id									deptId			");
        query.append("		,(SELECT description										");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc		");
        query.append("		,x.main_mng_id								mainMngId		");
        query.append("		,(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngName		");
        query.append("		,x.sub_mng_id								subMngId		");
        query.append("		,(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngName		");
        query.append("		,x.remark									remark			");
        query.append("		,x.ord_no									ordNo			");
        query.append("		,x.old_eq_no								oldEqNo			");
        query.append("		,x.serial_no								serialNo		");
        query.append("      ,x.pmi_action_type                      	pmiActionType   ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.pmi_action_type,'PMI_ACTION_TYPE','SYS','','"+user.getLangId()+"')  pmiActionTypeDesc        ");
        query.append("		,x.as_vendor								asVendor		");
        query.append("		,x.as_name									asName			");
        query.append("		,x.as_phone									asPhone			");
        query.append("		,x.eq_grade									eqGradeId		");
        query.append("		,x.excel_no									excelNo			");
        query.append("		,dbo.SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS','','"+user.getLangId()+"')	eqGradeDesc ");
        query.append("		,x.p_equip_id							    parentEquipId	");
        query.append("		,y.item_no								    parentItemNo	");
        query.append("		,y.description								parentEquipDesc ");
        query.append("		,x.is_last_version							isLastVersion 	");
        query.append("		,x.revisionhist_id							revisionHistId	");
        query.append("		,x.revision_status							revisionStatusId");
        query.append("      ,x.prod_shape                               prodShape    	");
        query.append("      ,x.eqstrloc_no       						eqStrLocNo      ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.supplier            						supplier        ");
        query.append("      ,x.country_maker     						countryMaker    ");
        query.append("		,x.ctctr_id 								ctCtrId			");
        query.append("		,(SELECT a.description										");
        query.append("		  FROM TACTCTR a											");
        query.append("		  WHERE a.ctctr_id = x.ctctr_id								");
        query.append("		    AND a.comp_no  = x.comp_no)				ctctrDesc		");
        query.append("      ,x.eq_spec                              	eqSpec		 	");
        query.append("      ,x.usage_dept                           	usageDeptId		");
        query.append("      ,(SELECT a.description                                      ");
        query.append("         FROM TADEPT a                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id = x.usage_dept)       	usageDeptDesc	");
        query.append("      ,x.usage_emp                            	userId			");
        query.append("      ,(SELECT a.emp_name                                         ");
        query.append("         FROM TAEMP a                                             ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.emp_id = x.usage_emp)         	userName    	");
        query.append("		,x.tag_no 									tagNo			");
        query.append("      ,x.eq_size                                  eqSize			");
        query.append("      ,x.weight                                   weight			");
        query.append("		,x.upd_date									updDate			");
        query.append("		,x.cre_date									creDate			");
        query.append("      ,x.lnwrklist_id                             lnWrkListId     ");
        query.append("      ,(SELECT aa.description                                     ");
        query.append("      FROM TALNWRKLIST aa                                         ");
        query.append("      WHERE x.comp_no = aa.comp_no                                ");
        query.append("      AND x.lnwrklist_id = aa.lnwrklist_id)       lnWrkListDesc   ");
        query.append("      ,x.currency									currencyId		");
        query.append("      ,dbo.SFACODE_TO_DESC(x.currency,'CURRENCY','USR',x.comp_no,'"+user.getLangId()+"')			currencyDesc	");
        query.append("		,x.eqctg_id									originEqCtgId	");
        query.append("		,x.plant 									plant			");
        query.append("  	,(SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant ) plantDesc		");
        query.append("		, x.wkctr_id						 		wkctrId			");
        query.append("		,(SELECT a.description 										");
        query.append("			FROM TAWKCTR a 											");
        query.append("			WHERE a.comp_no = x.comp_no 							");
        query.append("			AND a.wkctr_id = x.wkctr_id) 			wkctrDesc		");
        query.append("       ,x.comp_no compNo                                          ");
        query.append("       ,x.storage 								storage         ");
        query.append("       ,x.guaranty_date                           guarantyDate    ");
        query.append("FROM   TAEQUIPMENT x left outer join TAEQUIPMENT y on x.comp_no = y.comp_no and x.p_equip_id = y.equip_id  ");
        query.append("WHERE  x.equip_id = ?													");
        query.append("  AND  x.comp_no  = ?													");
        
        Object[] objects = new Object[] {
        		equipId,
        		user.getCompNo()
    	};
        MaEqMstrDetailDTO maEqMstrDetailDTO = 
        		(MaEqMstrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqMstrDetailDTO()));
        
        return maEqMstrDetailDTO;
    }
    
    public MaEqMoldMstrDetailDTO findMoldDetail(String equipId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT															");
        query.append("		x.equip_id									equipId,		");
        query.append("		x.eqmold_atype								eqMoldAtype,	");
        query.append("		dbo.SFACODE_TO_DESC(x.eqmold_atype,'EQMOLD_ATYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"')	eqMoldAtypeDesc,");
        query.append("		x.eqmold_btype								eqMoldBtype,	");
        query.append("		dbo.SFACODE_TO_DESC(x.eqmold_btype,'EQMOLD_BTYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"')	eqMoldBtypeDesc");
        query.append("		,x.eqmold_ctype																			eqMoldCtype			");
        query.append("		,dbo.SFACODE_TO_DESC(x.eqmold_ctype,'EQMOLD_CTYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"')	eqMoldCtypeDesc		");
        query.append("		,x.cavity																				cavity				");
        query.append("		,x.storage																				storage				");
        query.append("		,x.setcnt																				setcnt				");
        query.append("		,x.output																				output				");
        query.append("		,x.ownership																			ownership			");
        query.append("		,x.use_period																			usePeriod			");
        query.append("		,x.is_disuse																			isDisuse			");
        query.append("		,x.disuse_amt																			disuseAmt			");
        query.append("		,x.disuse_date																			disusedDate			");
        query.append("		,x.disuse_vendor																		disuseVendor		");
        query.append("		,x.stepno																				stepno				");
        query.append("FROM   TAEQMOLD x													");
        query.append("WHERE  x.equip_id = ?												");
        query.append("  AND  x.comp_no  = ?												");
        
        Object[] objects = new Object[] {
        		equipId,
        		user.getCompNo()
    	};
        
    	MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO = 
        		(MaEqMoldMstrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqMoldMstrDetailDTO()));
        
        return maEqMoldMstrDetailDTO;
    }
    
	public MaEqToolMstrDetailDTO findToolDetail(String equipId, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		query.append("SELECT															");
        query.append("		x.equip_id									equipId		    ");
        query.append("		,x.measure_unit								measureUnit	    ");
        query.append("		,x.guage_type								guaTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.guage_type,'GUAGE_TYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"')	guaTypeDesc	");
        query.append("		,x.is_standard_eq							isStandardEq	");
        query.append("		,x.base_equip_id								baseEquipId	");
        query.append("		,(SELECT a.description										");
        query.append("		   FROM TAEQUIPMENT a										");
        query.append("		  WHERE a.comp_no = x.comp_no								");
        query.append("			AND	a.equip_id = x.base_equip_id) 		baseEquipDesc	");
        query.append("		,x.min_unit_value							minUnitVal		");
        query.append("		,x.min_value								minVal			");
        query.append("		,x.max_value								maxVal			");
        query.append("		,x.measure_tool								measureTool	    ");
        query.append("		,x.measure_categ							measureCateg	");
        query.append("		,x.pmc_type								    pmcTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.pmc_type,'PMC_TYPE','SYS','','"+user.getLangId()+"')	pmcTypeDesc	");
        query.append("		,x.pmcalibstdtp_id							pmCalibStdTpId	");
        query.append("		,(SELECT a.measure_range									");
        query.append("		   FROM TAPMCALIBSTDTP a									");
        query.append("		  WHERE a.comp_no = x.comp_no								");
        query.append("			AND	a.pmcalibstdtp_id = x.pmcalibstdtp_id) pmCalibStdTpRange	");
        query.append("      ,x.all_range                                allRange        ");
        query.append("      ,x.use_range                                useRange        ");
        query.append("      ,x.accuracy                                 accuracy         ");
        query.append("      ,x.tolerance                                tolerance        ");
        query.append("FROM   TAEQTOOL x                                                  ");
        query.append("WHERE  x.equip_id = ?												");
        query.append("  AND  x.comp_no  = ?												");
        
        Object[] objects = new Object[] {
        		equipId,
        		user.getCompNo()
    	};

        MaEqToolMstrDetailDTO maEqToolMstrDetailDTO = 
        		(MaEqToolMstrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqToolMstrDetailDTO()));
        
        return maEqToolMstrDetailDTO;
	}

	public MaEqBuildMstrDetailDTO findBuildDetail(String equipId, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT													");
        query.append("		x.equip_id							equipId,		");
        query.append("		x.addr								eqBuildAddr,		");
        query.append("		x.area								eqBuildArea,		");
        query.append("		x.struct							eqBuildStruct,	");
        query.append("		x.floor								eqBuildFloor,	");
        query.append("		x.section							eqBuildSection	");
        query.append("FROM   TAEQBUILDING x										");
        query.append("WHERE  x.equip_id = ?										");
        query.append("  AND  x.comp_no  = ?										");
        
        Object[] objects = new Object[] {
        		equipId,
        		user.getCompNo()
    	};
        
        MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO = 
        		(MaEqBuildMstrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqBuildMstrDetailDTO()));
        
        return maEqBuildMstrDetailDTO;
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
	public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQUIPMENT						");
    	query.append("	(comp_no		,equip_id		,item_no	");
    	query.append("	 ,description	,eqloc_id		,eqctg_id	");
    	query.append("	 ,eq_status		,dept_id		,main_mng_id");
    	query.append("	 ,sub_mng_id	,setup_date		,buy_amt	");
    	query.append("	 ,plf_type		,maker			,model_no	");
    	query.append("	 ,capacity		,util_capa		,is_law_eq	");
    	query.append("	 ,ord_no		,remark			,old_eq_no	");
    	query.append("	 ,as_phone		,eq_grade       ,plant		");
    	query.append("	 ,serial_no		,as_vendor		,as_name	");
    	query.append("	 ,eqctg_type    ,p_equip_id		,buy_date    ");
    	query.append("   ,disused_date  ,pmi_action_type ,prod_shape ");
    	query.append("   ,eqstrloc_no	,supplier		,country_maker");
    	query.append("	 ,ctctr_id	    ,eq_spec		,usage_dept	");
    	query.append("	 ,usage_emp		,upd_by			,upd_date	");
    	query.append("	 ,tag_no		,eq_size		,weight		");
    	query.append("  ,cre_date	    ,cre_by			,lnwrklist_id");
    	query.append("  ,currency		,wkctr_id		,storage	");
    	query.append("  ,guaranty_date	,excel_no					");
    	query.append("	)	VALUES									");
    	query.append("	(? 				,? 				,? 			");
    	query.append("	 ,? 			,? 				,? 			");
    	query.append("	 ,? 			,? 				,? 			");
    	query.append("	 ,? 			,? 				,? 			");
		query.append("	 ,? 	        ,? 			    ,? 			");
		query.append("	 ,? 	        ,? 			    ,? 			");
		query.append("	 ,? 	        ,? 			    ,? 			");
		query.append("	 ,? 	        ,? 			    ,? 			");
		query.append("	 ,? 	        ,? 			    ,? 			");
    	query.append("	 ,?             ,? 			    ,? 			");
    	query.append("   ,?             ,?              ,?          ");
    	query.append("   ,?             ,?              ,?          ");
        query.append("   ,?             ,?              ,?          ");
        query.append("   ,?             ,?              ,?     		");
	    query.append("   ,? 		    ,? 			    ,? 			");
	    query.append("	 ,? 		    ,? 			    ,? 			");
	    query.append("	 ,? 		    ,?				,?		    ");
	    query.append("   ,?				,?							");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
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
    			maEqMstrDetailDTO.getAsPhone(),
    			maEqMstrDetailDTO.getEqGradeId(),
    			maEqMstrDetailDTO.getPlant(),
    			maEqMstrDetailDTO.getSerialNo(),
    			maEqMstrDetailDTO.getAsVendor(),
    			maEqMstrDetailDTO.getAsName(),
    			maEqMstrDetailDTO.getEqCtgTypeId(),
    			maEqMstrDetailDTO.getParentEquipId(),
    			maEqMstrDetailDTO.getBuyDate(),
    			maEqMstrDetailDTO.getDisusedDate(),
    			maEqMstrDetailDTO.getPmiActionType(),
    			maEqMstrDetailDTO.getProdShape(),
    			maEqMstrDetailDTO.getEqStrLocNo(),
    			maEqMstrDetailDTO.getSupplier(),
    			maEqMstrDetailDTO.getCountryMaker()
                ,maEqMstrDetailDTO.getCtCtrId()
                ,maEqMstrDetailDTO.getEqSpec()
                ,maEqMstrDetailDTO.getUsageDeptId()
                ,maEqMstrDetailDTO.getUserId()
                ,loginUser.getUserId()
                ,maEqMstrDetailDTO.getUpdDate()
                ,maEqMstrDetailDTO.getTagNo()
                ,maEqMstrDetailDTO.getEqSize()
                ,maEqMstrDetailDTO.getWeight()
                ,maEqMstrDetailDTO.getCreDate()
                ,loginUser.getUserId()
                ,maEqMstrDetailDTO.getLnWrkListId()
                ,maEqMstrDetailDTO.getCurrencyId()
                ,maEqMstrDetailDTO.getWkctrId()
                ,maEqMstrDetailDTO.getStorage()
                ,maEqMstrDetailDTO.getGuarantyDate()
                ,maEqMstrDetailDTO.getExcelNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int mergeMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("MERGE INTO TAEQMOLD a																					");
    	query.append("USING(	SELECT 	? compNo,																			");
    	query.append("					? equipId,																			");
    	query.append("					? eqMoldAtype,																		");
    	query.append("					? eqMoldBtype,																		");
    	query.append("					? eqMoldCtype,																		");
    	query.append("					? cavity,																			");
    	query.append("					? storage,																			");
    	query.append("					? setcnt,																			");
    	query.append("					? output,																			");
    	query.append("					? ownership,																		");
    	query.append("					? use_period,																		");
    	query.append("					? is_disuse,																		");
    	query.append("					? disuse_amt,																		");
    	query.append("					? disuse_date,																		");
    	query.append("					? disuse_vendor,																	");
    	query.append("					? stepno																			");
    	query.append("		) b																								");
    	query.append("ON(	a.comp_no = b.compNo																			");
    	query.append("	AND a.equip_id = b.equipId	)																		");
    	query.append("WHEN MATCHED THEN																						");
    	query.append("	UPDATE SET 	a.eqmold_atype = b.eqMoldAtype,															");
    	query.append("				a.eqmold_btype = b.eqMoldBtype,															");
    	query.append("				a.eqmold_ctype = b.eqMoldCtype,															");
    	query.append("				a.cavity = b.cavity,																	");
    	query.append("				a.storage = b.storage,																	");
    	query.append("				a.setcnt = b.setcnt,																	");
    	query.append("				a.output = b.output,																	");
    	query.append("				a.ownership = b.ownership,																");
    	query.append("				a.use_period = b.use_period,															");
    	query.append("				a.is_disuse = b.is_disuse,																");
    	query.append("				a.disuse_amt = b.disuse_amt,															");
    	query.append("				a.disuse_date = b.disuse_date,															");
    	query.append("				a.disuse_vendor = b.disuse_vendor,														");
    	query.append("				a.stepno = b.stepno																		");
    	query.append("WHEN NOT MATCHED THEN																					");
	    query.append("	INSERT (comp_no,		equip_id,		eqmold_atype,			eqmold_btype,		eqmold_ctype,							");
	    query.append("			cavity,			storage,		setcnt,					output,				ownership,								");
	    query.append("			use_period,		is_disuse,		disuse_amt,				disuse_date,		disuse_vendor, 		stepno)				");
    	query.append("	VALUES (b.compNo,		b.equipId,		b.eqMoldAtype,			b.eqMoldBtype,		b.eqMoldCtype,							");
    	query.append("			b.cavity,		b.storage,		b.setcnt,				b.output,			b.ownership,							");
    	query.append("			b.use_period,	b.is_disuse,	b.disuse_amt,			b.disuse_date,		b.disuse_vendor,	 b.stepno);			");
    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMoldMstrDetailDTO.getEqMoldAtype(),
    			maEqMoldMstrDetailDTO.getEqMoldBtype(),
    			maEqMoldMstrDetailDTO.getEqMoldCtype(),
    			maEqMoldMstrDetailDTO.getCavity(),
    			maEqMoldMstrDetailDTO.getStorage(),
    			CommonUtil.getRowMoneyToNum(maEqMoldMstrDetailDTO.getSetCnt()),
    			maEqMoldMstrDetailDTO.getOutPut(),
    			maEqMoldMstrDetailDTO.getOwnerShip(),
    			maEqMoldMstrDetailDTO.getUsePeriod(),
    			maEqMoldMstrDetailDTO.getIsDisUse(),
    			CommonUtil.getRowMoneyToNum(maEqMoldMstrDetailDTO.getDisUseAmt()),
    			maEqMoldMstrDetailDTO.getDisusedDate(),
    			maEqMoldMstrDetailDTO.getDisUseVendor(),
    			maEqMoldMstrDetailDTO.getStepNo()
    	};
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
	public int mergeToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("MERGE INTO TAEQTOOL a																		");
    	query.append("USING(	SELECT 	? compNo																");
    	query.append("					,? equipId																");
    	query.append("                    ,? guaTypeId                                                          ");
    	query.append("					,? measureUnit															");
    	query.append("					,? isStandardEq															");
    	query.append("					,? baseEquipId															");
    	query.append("					,? measureTool															");
    	query.append("					,? measureCateg															");
    	query.append("					,? minUnitVal															");
    	query.append("					,? minVal																");
    	query.append("					,? maxVal																");
    	query.append("					,? pmcTypeId															");
    	query.append("					,? pmCalibStdTpId														");
    	query.append("					,? allRange																");
    	query.append("					,? useRange																");
    	query.append("                  ,? accuracy                                                             ");
        query.append("                  ,? tolerance                                                            ");
    	query.append("	   ) b																                  	");
    	query.append("ON(	a.comp_no = b.compNo																");
    	query.append("	AND a.equip_id = b.equipId	)															");
    	query.append("WHEN MATCHED THEN																			");
    	query.append("	UPDATE SET 	a.guage_type = b.guaTypeId										    		");
        query.append("                ,a.measure_unit = b.measureUnit                                           ");
    	query.append("				,a.is_standard_eq = b.isStandardEq											");
    	query.append("				,a.base_equip_id = b.baseEquipId											");
    	query.append("				,a.measure_tool = b.measureTool												");
    	query.append("				,a.measure_categ = b.measureCateg											");
    	query.append("				,a.min_unit_value = b.minUnitVal											");
    	query.append("				,a.min_value = b.minVal														");
    	query.append("				,a.max_value = b.maxVal														");
    	query.append("				,a.pmc_type = b.pmcTypeId													");
    	query.append("				,a.pmcalibstdtp_id = b.pmCalibStdTpId										");
    	query.append("				,a.all_range = b.allRange													");
    	query.append("				,a.use_range = b.useRange													");
    	query.append("              ,a.accuracy  = b.accuracy                                                   ");
        query.append("              ,a.tolerance = b.tolerance                                                  ");
    	query.append("WHEN NOT MATCHED THEN																		");
	    query.append("	INSERT (comp_no,			equip_id,		guage_type, 	    measure_unit,		is_standard_eq,	    ");
	    query.append("			base_equip_id,	    measure_tool,	measure_categ,	    min_unit_value,	    min_value,		    ");
	    query.append("			max_value,		    pmc_type,		pmcalibstdtp_id,	all_range,		    use_range,		    ");
	    query.append("          accuracy,           tolerance                                                                   ");
    	query.append("	)VALUES(b.compNo,			b.equipId,		b.guaTypeId,        b.measureUnit,		b.isStandardEq,		");
    	query.append("			b.baseEquipId,		b.measureTool,  b.measureCateg,		b.minUnitVal,		b.minVal,			");
    	query.append("			b.maxVal,			b.pmcTypeId,	b.pmCalibStdTpId,	b.allRange,			b.useRange,			");
    	query.append("          b.accuracy,         b.tolerance                                                                 ");
    	query.append("	);																										");
    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maEqMstrDetailDTO.getEquipId()
    			,maEqToolMstrDetailDTO.getGuaTypeId()
    			,maEqToolMstrDetailDTO.getMeasureUnit()
    			,maEqToolMstrDetailDTO.getIsStandardEq()
    			,maEqToolMstrDetailDTO.getBaseEquipId()
    			,maEqToolMstrDetailDTO.getMeasureTool()
    			,maEqToolMstrDetailDTO.getMeasureCateg()
    			,maEqToolMstrDetailDTO.getMinUnitVal()
    			,maEqToolMstrDetailDTO.getMinVal()
    			,maEqToolMstrDetailDTO.getMaxVal()
    			,maEqToolMstrDetailDTO.getPmcTypeId()
    			,maEqToolMstrDetailDTO.getPmCalibStdTpId()
    			,maEqToolMstrDetailDTO.getAllRange()
    			,maEqToolMstrDetailDTO.getUseRange()
    			,maEqToolMstrDetailDTO.getAccuracy()
    			,maEqToolMstrDetailDTO.getTolerance()
    	};
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	public int mergeBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,	MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("MERGE INTO TAEQBUILDING a													");
    	query.append("USING(	SELECT 	? compNo,												");
    	query.append("					? equipId,												");
    	query.append("					? eqBuildAddr,											");
    	query.append("					? eqBuildArea,											");
    	query.append("					? eqBuildStruct,											");
    	query.append("					? eqBuildFloor,											");
    	query.append("					? eqBuildSection											");
    	query.append("		) b																	");
    	query.append("ON(	a.comp_no = b.compNo												");
    	query.append("	AND a.equip_id = b.equipId	)											");
    	query.append("WHEN MATCHED THEN															");
    	query.append("	UPDATE SET 	a.addr = b.eqBuildAddr,										");
    	query.append("				a.area = b.eqBuildArea,										");
    	query.append("				a.struct = b.eqBuildStruct,									");
    	query.append("				a.floor = b.eqBuildFloor,									");
    	query.append("				a.section = b.eqBuildSection									");
    	query.append("WHEN NOT MATCHED THEN														");
	    query.append("	INSERT (comp_no,		equip_id,		addr,			area,			");
	    query.append("			struct,			floor,			section						)	");
    	query.append("	VALUES (b.compNo,		b.equipId,		b.eqBuildAddr,	b.eqBuildArea,	");
    	query.append("			b.eqBuildStruct,	b.eqBuildFloor,	b.eqBuildSection				);	");
    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maEqMstrDetailDTO.getEquipId(),
    			maEqBuildMstrDetailDTO.getEqBuildAddr(),
    			maEqBuildMstrDetailDTO.getEqBuildArea(),
    			maEqBuildMstrDetailDTO.getEqBuildStruct(),
    			maEqBuildMstrDetailDTO.getEqBuildFloor(),
    			maEqBuildMstrDetailDTO.getEqBuildSection()
    	};
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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
    public int insertEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQALTHIST								");
    	query.append("	(comp_no,		eqalthist_id,		upd_date,		");
    	query.append("	 upd_by,		equip_id,			item_no,		");
    	query.append("	 description,	eqloc_id,			eqctg_id,		");
    	query.append("	 eq_status,		dept_id,			main_mng_id,	");
    	query.append("	 sub_mng_id,	is_law_eq,			remark			");
    	query.append("	)	VALUES											");
    	query.append("	(?,				NEXT VALUE FOR SQAEQALTHIST_ID,CONVERT(VARCHAR, GETDATE(), 112) +' '+ CONVERT(VARCHAR, GETDATE(), 108),	");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?			");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			loginUser.getUserId(),
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
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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
    public int insertEqPart(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAEQPART(                                   ");
    	query.append("     comp_no                                            ");
    	query.append("    ,eqpart_id                                          ");
    	query.append("    ,eq_ctg_part_id                                     ");
    	query.append("    ,eq_ctg_asmb_id                                     ");
    	query.append("    ,equip_id                                           ");
    	query.append("    ,part_id                                            ");
    	query.append("    ,consist_qty                                        ");
    	query.append(") SELECT                                                ");
    	query.append("           comp_no                                      ");
    	query.append("          ,NEXT VALUE FOR SQAEQPART_ID                  ");
    	query.append("          ,eq_ctg_part_id                               ");
    	query.append("          ,eq_ctg_asmb_id                               ");
    	query.append("          ,?                                            ");
    	query.append("          ,part_id                                      ");
    	query.append("          ,use_qty                                     ");
    	query.append("FROM TAEQCTGPART                                       ");
    	query.append(" WHERE comp_no  = ?                                    ");
    	query.append("          AND eqctg_id = ?                             ");
    	query.append("          and is_use = 'Y'                             ");

    	
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEquipId(),
    			loginUser.getCompNo(),
    			maEqMstrDetailDTO.getEqCtgId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
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
    public int updateEqPart(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAEQPART(                                   ");
    	query.append("     comp_no                                            ");
    	query.append("    ,eqpart_id                                          ");
    	query.append("    ,eq_ctg_part_id                                     ");
    	query.append("    ,eq_ctg_asmb_id                                     ");
    	query.append("    ,equip_id                                           ");
    	query.append("    ,part_id                                            ");
    	query.append("    ,consist_qty                                        ");
    	query.append(") SELECT                                                ");
    	query.append("           a.comp_no                                    ");
    	query.append("          ,NEXT VALUE FOR SQAEQPART_ID                  ");
    	query.append("          ,a.eq_ctg_part_id                             ");
    	query.append("          ,a.eq_ctg_asmb_id                             ");
    	query.append("          ,?                                            ");
    	query.append("          ,a.part_id                                    ");
    	query.append("          ,a.use_qty                                    ");
    	query.append("FROM TAEQCTGPART a                                      ");
    	query.append(" WHERE a.comp_no  = ?                                    ");
    	query.append("          AND a.eqctg_id = ?                             ");
    	query.append("          and a.is_use = 'Y'                             ");
    	query.append("          and not exists (select 1 from TAEQPART aa           ");
    	query.append("               where a.comp_no = aa.comp_no                   ");
    	query.append("                   and a.eq_ctg_part_id = aa.eq_ctg_part_id   ");
    	query.append("              )                                               ");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEquipId(),
    			maEqMstrDetailDTO.getCompNo(),
    			maEqMstrDetailDTO.getEqCtgId()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    }
    
    /**
     * TAEQASSLIST default 데이터 생성
     * 저장시 설비등급평가 추가
     * @author youngjoo38
     * @version $Id: MaEqMstrDetailDTO.java,v 1.0 2017/11/03 16:39:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param compNo, equipId
     * @return
     */
    public int insertAssDetail(User loginUser, String equipId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String eqAssListId = getNextSequence("SQAEQASSLIST_ID");
        
        query.append("insert into TAEQASSLIST(                           ");
        query.append("    comp_no, eqasslist_id, eqasslist_no, equip_id  ");
        query.append("    ,eqasslist_status, ass_date, dept_id, emp_id   ");
        query.append("    ,assbaselist_id                                ");
        query.append("    ,eqasslist_type                                ");
        query.append(")                                                  ");
        query.append("select                                             ");
        query.append("    x.comp_no                                      ");
        query.append("    ,?                                             ");
        query.append("    ,?                                             ");
        query.append("    ,x.equip_id                                    ");
        query.append("    ,'W'                                           ");
        query.append("    , ?                                            ");
        query.append("    , ?		                                     ");
        query.append("    , ?	                                 		 ");
        query.append("    ,(SELECT a.assbaselist_id FROM TAASSBASELIST a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id) assbaselist_id		");
        query.append("    , ?											 ");
        query.append("from taequipment x                                 ");
        query.append("where x.comp_no = ?                                ");
        query.append("    and x.equip_id =  ?                            ");
        
        Object[] objects = new Object[] {
                 eqAssListId
                ,eqAssListId
                ,DateUtil.getDate()
                ,loginUser.getDeptId()
                ,loginUser.getEmpId()
                ,"LT"
                ,loginUser.getCompNo()
                ,equipId
        };
        
        this.getJdbcTemplate().update(query.toString(), getObject(objects));
        
        query.setClear();
        query.append("INSERT INTO TAEQASSPVAL (     ");
        query.append("    eqasslist_id              ");
        query.append("  , comp_no                   ");
        query.append("  , eqasspval_id              ");
        query.append("  , assbasepoint_id           ");
        query.append("  , ass_point_type            ");
        query.append("  , ass_point                 ");
        query.append("  ) SELECT                    ");
        query.append("  ?                           ");
        query.append(", ?                           ");
        query.append(", NEXT VALUE FOR SQAEQASSPVAL_ID  ");
        query.append(", x.assbasepoint_id           ");
        query.append(", x.ass_point_type            ");
        query.append(", x.ass_point                 ");
        query.append("FROM TAASSBASEPOINT x         ");
        query.append("WHERE x.assbaselist_id = (SELECT a.assbaselist_id FROM TAEQASSLIST a 		");
        query.append("                           WHERE a.comp_no = x.comp_no AND a.eqasslist_id = ? )		");
        query.append("    and x.comp_no =  ?        ");
        
        objects = new Object[] {
                  eqAssListId
                , loginUser.getCompNo()
                , eqAssListId
                , loginUser.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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
    public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET				    		");
    	query.append("	description		= ? , eqloc_id		    = ?		");
    	query.append("	, eqctg_id		= ? , maker			    = ?		");
    	query.append("	, model_no		= ? , capacity		    = ?		");
    	query.append("	, setup_date	= ? , plf_type		    = ?		");
    	query.append("	, util_capa		= ? , dept_id		    = ?		");
    	query.append("	, buy_amt		= ? , is_law_eq		    = ?		");
    	query.append("	, main_mng_id	= ? , sub_mng_id	    = ?		");
    	query.append("	, ord_no		= ? , remark		    = ?		");
    	query.append("	, eq_status		= ? , old_eq_no		    = ?		");
    	query.append("	, serial_no		= ? , as_vendor		    = ?		");
    	query.append("	, as_name		= ? , as_phone		    = ?		");
    	query.append("	, eq_grade		= ? , eqctg_type	    = ?		");
    	query.append("	, p_equip_id    = ? , buy_date		    = ?     ");
    	query.append("  , disused_date  = ? , pmi_action_type   = ?     ");
    	query.append("  , prod_shape    = ? , eqstrloc_no 	    = ?     ");
    	query.append("  , supplier      = ? , country_maker     = ?   	");
        query.append("	, ctctr_id		= ? , eq_spec		    = ?		");
        query.append("	, usage_dept	= ? , usage_emp		    = ?		");
        query.append("  , upd_by        = ? , upd_date          = ?		");
        query.append("  , tag_no        = ? , eq_size		    = ?		");
        query.append("  , weight		= ? , lnwrklist_id	    = ?		");
        query.append("  , currency      = ? , plant			    = ?		");
        query.append("  , wkctr_Id      = ?	, storage			= ?	    ");
        query.append("  , guaranty_date = ?	, excel_no			= ?		");
        query.append("  , item_no = ?									");
        query.append("WHERE equip_id 	= ?							    ");
    	query.append("  AND comp_no		= ?							    ");
    	
    	Object[] objects = new Object[] {
    			maEqMstrDetailDTO.getEquipDesc()
    			,maEqMstrDetailDTO.getEqLocId()
    			,maEqMstrDetailDTO.getEqCtgId()
    			,maEqMstrDetailDTO.getMaker()
    			,maEqMstrDetailDTO.getModelNo()
    			,maEqMstrDetailDTO.getCapacity()
    			,CommonUtil.getRowDateToNum(maEqMstrDetailDTO.getSetupDate())
    			,maEqMstrDetailDTO.getPlfTypeId()
    			,maEqMstrDetailDTO.getUtilCapa()
    			,maEqMstrDetailDTO.getDeptId()
    			,CommonUtil.getRowMoneyToNum(maEqMstrDetailDTO.getBuyAmt())
    			,maEqMstrDetailDTO.getIsLawEq()
    			,maEqMstrDetailDTO.getMainMngId()
    			,maEqMstrDetailDTO.getSubMngId()
    			,maEqMstrDetailDTO.getOrdNo()
    			,maEqMstrDetailDTO.getRemark()
    			,maEqMstrDetailDTO.getEqStatusId()
    			,maEqMstrDetailDTO.getOldEqNo()
    			,maEqMstrDetailDTO.getSerialNo()
    			,maEqMstrDetailDTO.getAsVendor()
    			,maEqMstrDetailDTO.getAsName()
    			,maEqMstrDetailDTO.getAsPhone()
    			,maEqMstrDetailDTO.getEqGradeId()
    			,maEqMstrDetailDTO.getEqCtgTypeId()
    			,maEqMstrDetailDTO.getParentEquipId()
    			,CommonUtil.getRowDateToNum(maEqMstrDetailDTO.getBuyDate())
    			,CommonUtil.getRowDateToNum(maEqMstrDetailDTO.getDisusedDate())
    			,maEqMstrDetailDTO.getPmiActionType()
    			,maEqMstrDetailDTO.getProdShape()
    			,maEqMstrDetailDTO.getEqStrLocNo()
    			,maEqMstrDetailDTO.getSupplier()
    			,maEqMstrDetailDTO.getCountryMaker()
                ,maEqMstrDetailDTO.getCtCtrId()
                ,maEqMstrDetailDTO.getEqSpec()
                ,maEqMstrDetailDTO.getUsageDeptId()
                ,maEqMstrDetailDTO.getUserId()
                ,loginUser.getUserId()
                ,CommonUtil.getRowDateToNum(maEqMstrDetailDTO.getUpdDate())
                ,maEqMstrDetailDTO.getTagNo()
                ,maEqMstrDetailDTO.getEqSize()
                ,maEqMstrDetailDTO.getWeight()
                ,maEqMstrDetailDTO.getLnWrkListId()
                ,maEqMstrDetailDTO.getCurrencyId()
                ,maEqMstrDetailDTO.getPlant()
                ,maEqMstrDetailDTO.getWkctrId()
                ,maEqMstrDetailDTO.getStorage()
                ,CommonUtil.getRowDateToNum(maEqMstrDetailDTO.getGuarantyDate())
                ,maEqMstrDetailDTO.getExcelNo()
                ,maEqMstrDetailDTO.getItemNo()
    			,maEqMstrDetailDTO.getEquipId()
    			,loginUser.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPMEQUIP SET							");
    	query.append("	   is_active	= 'N'						");
    	query.append("    ,init_wrk_date = ?                        ");
    	query.append("    ,last_sch_date = ?                        ");
    	query.append("    ,next_sch_date = ?                        ");
    	query.append("WHERE equip_id    = ?  		                ");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
    			 DateUtil.getDate()
    			,DateUtil.getDate()
    			,DateUtil.getDate()
    			,maEqMstrDetailDTO.getEquipId()
    			,maEqMstrDetailDTO.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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
    public int deleteSchedAllPmEquip(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("exec dbo.SP_PM_MAKE_SCHEDEQUIP_DELETE                          ");
        query.append("                  '"+maEqMstrDetailDTO.getCompNo()+"'          ");
        query.append("                 , "+maEqMstrDetailDTO.getEquipId()+"          ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
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
    public List findReportEquipDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		String lang = maEqMstrDetailDTO.getUserLang();
		
        query.append("SELECT																										");
        query.append("		x.equip_id									EQUIPID,													");
        query.append("		x.item_no									ITEMNO,														");
        query.append("		x.description								EQUIPDESC,													");
        query.append("		x.maker										MAKER,														");
        query.append("		x.model_no									MODEL,														");
        query.append("		(SELECT TOP 1 (SELECT description 																		");
        query.append("					FROM TAASSET																				");
        query.append("					WHERE asset_id = a.asset_id																	");
        query.append("					  AND comp_no = a.comp_no)																	");
        query.append("			FROM TAEQASSET a																					");
        query.append("			WHERE a.comp_no = x.comp_no																			");
        query.append("			  AND a.equip_id = x.equip_id																		");
        query.append("		) ASSETNO,																								");
        query.append("		dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"') EQSTATUSDESC,				");
        query.append("		dbo.SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS','','"+user.getLangId()+"') EQGRADEDESC,					");
        query.append("		CONVERT(DATE, x.setup_date) SETUPDATE,																	");
        query.append("		 x.capacity									CAPACITY,													");
        query.append("		 x.util_capa								UTILCAPA,													");
        query.append("		 x.buy_amt									BUYAMT,														");
        query.append("		(SELECT description																						");
        query.append("		   FROM TADEPT																							");
        query.append("		  WHERE comp_no = x.comp_no																				");
        query.append("		    AND dept_id = x.dept_id)				DEPTDESC,													");
        query.append("		(SELECT full_desc																						");
        query.append("		   FROM TAEQLOC																							");
        query.append("		  WHERE comp_no = x.comp_no																				");
        query.append("		    AND eqloc_id = x.eqloc_id)				EQLOCDESC,													");
        query.append("		CONVERT(VARCHAR, GETDATE(), 23) +' '+ LEFT(CONVERT(VARCHAR, GETDATE(), 108),5) TODAY,										");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqGrade' AND key_type='LABEL') EQGRADE,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='rPrList' AND key_type='LABEL') RPRLIST,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='title' AND key_type='LABEL') TITLE,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='location' AND key_type='LABEL') EQLOCATION,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo' AND key_type='LABEL') EQUIPNO,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqMainHist' AND key_type='LABEL') EQMAINHIST,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='deptDesc' AND key_type='LABEL') EQDEPTDESC,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') EQUIPNAME,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='setupDate' AND key_type='LABEL') EQSETUPDATE,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='setupAmt' AND key_type='LABEL') SETUPAMT,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='maker' AND key_type='LABEL') EQMAKER,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='model' AND key_type='LABEL') EQMODEL,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqPtSizeList' AND key_type='LABEL') EQPTSIZELIST,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqPart' AND key_type='LABEL') EQPART,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repChgList' AND key_type='LABEL') REPCHGLIST,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repairPart' AND key_type='LABEL') REPAIRPART,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='EQPHOTO' AND key_type='LABEL') EQPHOTO,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqHistCard' AND key_type='LABEL') EQHISTCARD,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='yyyymmdd' AND key_type='LABEL') REPYYYYMMDD,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='repChgDesc' AND key_type='LABEL') REPCHGDESC,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') MANAGER,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woNo' AND key_type='LABEL') WONO,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partCode' AND key_type='LABEL') PARTCODE,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') PARTNAMESIZE,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') EQPARTREMARK					");
        query.append("FROM   TAEQUIPMENT x																							");
        query.append("WHERE  x.equip_id = '"+maEqMstrDetailDTO.getEquipId()+"'														");
        query.append("  AND  x.comp_no  = '"+user.getCompNo()+"'														");

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

		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT (SELECT description								");
        query.append("			FROM TAEQASMB 									");
        query.append("			WHERE equip_id = x.equip_id						");
        query.append("			  AND comp_no = x.comp_no						");
        query.append("			  AND eqasmb_id= x.eqasmb_id) EQPARTTYPE,		");
        query.append("		ISNULL(y.description,'')+'('+ISNULL(y.pt_size,'')+')'  EQPARTDESC,		");
        query.append("		y.part_no PARTNO									");
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

		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT CONVERT(DATE, x.wkor_date) REPENDDATE,				");
        query.append("		x.description REPDESC,								");
        query.append("		(SELECT emp_name									");
        query.append("		FROM TAEMP											");
        query.append("		WHERE comp_no=x.comp_no								");
        query.append("		  AND emp_id = x.emp_id) REPMANAGER,				");
        query.append("		x.wo_no REPWONO										");
        query.append("FROM TAWORKORDER x										");
        query.append("WHERE 1=1													");
        query.append("AND wkor_id IN (SELECT wkor_id							");
        query.append("					FROM TAWOEQUIP							");
        query.append("					WHERE comp_no = x.comp_no				");
        query.getAndQuery("equip_id", maEqMstrDetailDTO.getEquipId());
        query.append("					)										");
        query.append("  AND x.comp_no='"+maEqMstrDetailDTO.getCompNo()+"'		");
        query.append("order by x.wkor_date										");

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

		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT CONVERT(DATE, x.end_date) oilEndDate,				");
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
        query.append("WHERE x.pm_type IN ('OIL')								");
        query.append("  AND x.equip_id="+maEqMstrDetailDTO.getEquipId()+"		");
        query.append("  AND x.comp_no='"+maEqMstrDetailDTO.getCompNo()+"'		");
        query.append("order by x.end_date										");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    
    public List findReportWoImgDetail(String objectId, String objectType) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		String fileDir = MwareConfig.getFileDir();
		
//		String fileDir1 = "D:\\mware_data\\File_data\\file\\100\\IMAGE\\201706\\315";

		query.append("SELECT                                        ");
		if(MwareConfig.osName.indexOf("LINUX") >=0){
			query.append("       '"+fileDir+"' + CONVERT(VARCHAR,x.nf_file_path) + '/' + CONVERT(VARCHAR,x.imgdata_id) AS FILENAME 	");
		}else{
			query.append("       '"+fileDir+"' + CONVERT(VARCHAR,x.nf_file_path) + '\\' + CONVERT(VARCHAR,x.imgdata_id) AS FILENAME 	");
		}
//        query.append("       '"+fileDir1+"' AS FILENAME 	");
        query.append("FROM   TAIMGDATA x                            ");
        query.append("WHERE x.image_id IN (SELECT a.image_id        ");
        query.append("                     FROM   TAIMAGE a         ");
        query.append("                     WHERE  a.object_id = '"+objectId+"'      ");
        query.append("                       AND  a.object_type = '"+objectType+"'  ");
        query.append("                                 )            ");

		return getJdbcTemplate().queryForList(query.toString());
		
	}
    public void SP_IF_UPD_TXEQUIPMENT(String equipId, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_IF_UPD_TXEQUIPMENT '"+loginUser.getCompNo()+"',"+equipId+",'"+loginUser.getUserNo()+"'     ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public String findEqPlant(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("select plant                                              ");
    	query.append("from taeqloc                                              ");
    	query.append("where comp_no = '"+maEqMstrDetailDTO.getCompNo()+"'       ");
    	query.append("    and eqloc_id = '"+maEqMstrDetailDTO.getEqLocId()+"'   ");
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));

    }

	@Override
	public MaEqDeviceMstrDetailDTO findDeviceDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		query.append("SELECT                                                    		");
		query.append("        x.equip_id                            equipId,        	");
		query.append("        x.comp_no                            compNo,            	");
		query.append("        x.part_id                            partId, 				");
		query.append("        y.description partDesc,           						");
		query.append("        x.wcode_id                    eqdeviceLocNo    	");
		query.append("        ,(SELECT a.wname FROM TAWAREHOUSE a WHERE a.wcode_id = x.wcode_id AND a.comp_no = x.comp_no)  eqdeviceLocNoDesc     		");
		query.append("FROM  TAEQDEVICE x INNER JOIN TAPARTS y ON x.part_id = y.part_id AND  x.comp_no = y.comp_no                                      		");
        query.append("WHERE x.equip_id = ?										");
        query.append("  AND x.comp_no  = ?										");
        
        Object[] objects = new Object[] {
        		maEqMstrCommonDTO.getEquipId(),
        		user.getCompNo()
    	};
        
       return (MaEqDeviceMstrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqDeviceMstrDetailDTO()));
	}
	
	@Override
	public AssetListITDetailDTO findITDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		String compNo = user.getCompNo();
        
        query.append("SELECT                                                            		");
        query.append("       ''                                     seqNo          		        ");
        query.append("     , ''                                     isDelCheck     		        ");
        query.append("     , x.equip_id                             equipId        		        ");
        query.append("     , x.item_no                              itemNo         		        ");
        query.append("     , (CASE x.eqctg_type WHEN 'MD' THEN '('+x.old_eq_no+')'+x.description ELSE x.description END ) eqDesc		");
        query.append(" 	   , x.eqctg_id     						eqCtgId		");
        query.append("     ,(SELECT a.full_desc FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id ) eqCtgDesc		");
        query.append("     , x.model_no                             model						");
        query.append("     , x.serial_no                            serialNo   					");
        query.append("     , x.buy_date                             buyDate						");
        query.append("     , x.eq_spec                              eqSpec		 				");
        query.append("     , ( SELECT a.ipaddr FROM TAEQIT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   ipAddr		");
        query.append("     , x.eq_status                                                                        eqStatusId		");
        query.append("     , dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','',?)    eqStatusDesc        		");
        query.append("     , x.eqloc_id								eqLocId					");
        query.append("     , (SELECT a.full_desc                                           		");
        query.append("         FROM TAEQLOC  a                                           		");
        query.append("        WHERE a.comp_no = x.comp_no                                 		");
        query.append("          AND a.eqloc_id = x.eqloc_id)        eqLocDesc    				");
        query.append("     , x.dept_id                              deptId						");
        query.append("     ,  (SELECT a.description                                         	");
        query.append("         FROM TADEPT a                                              		");
        query.append("        WHERE a.comp_no = x.comp_no                                 		");
        query.append("          AND a.dept_id = x.dept_id)          deptDesc					");
        query.append("     , x.main_mng_id                          mainMngId					");
        query.append("     , (SELECT a.emp_name                                            		");
        query.append("         FROM TAEMP a                                              		");
        query.append("        WHERE a.comp_no = x.comp_no                                 		");
        query.append("          AND a.emp_id = x.main_mng_id)       mainMngName					");
        query.append("     , x.sub_mng_id                           subMngId					");
        query.append("     , (SELECT a.emp_name                                            		");
        query.append("         FROM TAEMP a                                              		");
        query.append("        WHERE a.comp_no = x.comp_no                                 		");
        query.append("          AND a.emp_id = x.sub_mng_id)        subMngName					");
        query.append("	   , x.maker								makerDesc					");
        query.append("	   , x.as_vendor							asVendorName				");
        query.append("	   , x.supplier								supplierDesc				");
        query.append("     , x.usage_dept                           usageDeptId					");
        query.append("     ,  (SELECT a.description                                         	");
        query.append("         FROM TADEPT a                                              		");
        query.append("        WHERE a.comp_no = x.comp_no                                 		");
        query.append("          AND a.dept_id = x.usage_dept)       usageDeptDesc				");
        query.append("     , x.usage_emp                            userId						");
        query.append("     , (SELECT a.emp_name                                            		");
        query.append("         FROM TAEMP a                                              		");
        query.append("        WHERE a.comp_no = x.comp_no                                 		");
        query.append("          AND a.emp_id = x.usage_emp)         userName    				");
        query.append("     , dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','',?)    revisionStatus    		");
        query.append("     , x.is_last_version                      isLastVersion				");
        query.append("		,x.revisionhist_id							revisionHistId		");
        query.append("		,x.revision_status							revisionStatusId	");
        query.append("     , (SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param		");
        query.append("     , x.buy_amt								buyAmt						");
        query.append("     , y.os_name 								osName		");
        query.append("     , y.hostname 							hostName		");
        query.append("     , y.mac_addr 							macAddr		");
        query.append("     , x.remark 							    REMARK		");

        query.append("FROM   TAEQUIPMENT x  LEFT JOIN TAEQIT y		");
        query.append("ON x.comp_no = y.comp_no 		");
        query.append("AND x.equip_id = y.equip_id        		");
        query.append("WHERE  1 = 1                                                      		");
    	query.append("AND    x.equip_id = ?														");
    	query.append("AND    x.comp_no  = ?														");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,maEqMstrCommonDTO.getEquipId()
    			,user.getCompNo()
    	};
        
        return (AssetListITDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new AssetListITDetailDTO()));
	}

	@Override
	public int mergeDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,
			User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("MERGE INTO TAEQDEVICE a																	");
    	query.append("USING(	SELECT 	? compNo,																");
    	query.append("					? equipId,																");
    	query.append("					? partId,																");
    	query.append("					? wcodeId																");
    	query.append("	    ) b																					");
    	query.append("ON(	a.comp_no = b.compNo																");
    	query.append("	AND a.equip_id = b.equipId	)															");
    	query.append("WHEN MATCHED THEN																			");
    	query.append("	UPDATE SET 	a.part_id = b.partId,														");
    	query.append("				a.wcode_id = b.wcodeId														");
    	query.append("WHEN NOT MATCHED THEN																		");
	    query.append("	INSERT (comp_no,		equip_id,		part_id,			wcode_id	)				");
    	query.append("	VALUES (b.compNo,		b.equipId,		b.partId,			b.wcodeId		);			");
    	
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			maEqMstrDetailDTO.getEquipId(),
    			maEqDeviceMstrDetailDTO.getPartId(),
    			maEqDeviceMstrDetailDTO.getEqdeviceLocNo()
    	};
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	public int updateIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		QuerySqlBuffer query = new QuerySqlBuffer();

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
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	
	public int updateRevisionHist(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		QuerySqlBuffer query = new QuerySqlBuffer();

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
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	
	public int updateBeforeIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		QuerySqlBuffer query = new QuerySqlBuffer();

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
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int updateEqMstrLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO,
			User user, String histId) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET                				");
    	query.append("		revisionhist_id = ?                             ");
    	query.append("		,revision_status = ?                            ");
    	query.append("		,is_last_version = ?                            ");
    	query.append("WHERE comp_no = ?                       				");
    	query.append("AND   equip_id = ?                       				");

    	
    	Object[] objects = new Object[] {
    			histId
    			,"C"
    	        ,"Y"
    	        ,user.getCompNo()
    	        ,maEqMstrDetailDTO.getEquipId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
	
	@Override
	public int mergeITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User loginUser) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("MERGE INTO TAEQIT a																	");
		query.append("USING(	SELECT 	? compNo,															");
		query.append("					? equipId,															");
		query.append("					? ipaddr,															");
		query.append("					? os_name,															");
		query.append("					? hostname,															");
		query.append("					? mac_addr															");
		query.append("	    ) b																				");
		query.append("ON(	a.comp_no = b.compNo															");
		query.append("	AND a.equip_id = b.equipId	)														");
		query.append("WHEN MATCHED THEN																		");
		query.append("	UPDATE SET 	a.ipaddr = b.ipaddr,													");
		query.append("			 	a.os_name = b.os_name,													");
		query.append("			 	a.hostname = b.hostname,												");
		query.append("				a.mac_addr = b.mac_addr													");
		query.append("WHEN NOT MATCHED THEN																	");
		query.append("	INSERT (comp_no,	equip_id,	ipaddr,		os_name,	hostname,	mac_addr 	)	");
		query.append("	VALUES (b.compNo,	b.equipId,	b.ipaddr,	b.os_name,	b.hostname,	b.mac_addr	);	");
		
		
		Object[] objects = new Object[] {
				loginUser.getCompNo(),
				maEqMstrDetailDTO.getEquipId(),
				assetListITDetailDTO.getIpAddr(),
				assetListITDetailDTO.getOsName(),
				assetListITDetailDTO.getHostName(),
				assetListITDetailDTO.getMacAddr()
		};
		return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, String revisionHistId,String revisionStatus, User loginUser) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQUIPMENT									");
    	query.append("	( comp_no			,equip_id			,item_no		");
    	query.append("	 ,description		,eqloc_id			,eqctg_id		");
    	query.append("	 ,eq_status			,dept_id			,main_mng_id	");
    	query.append("	 ,sub_mng_id		,setup_date			,buy_amt		");
    	query.append("	 ,plf_type			,maker				,model_no		");
    	query.append("	 ,capacity			,util_capa			,is_law_eq		");
    	query.append("	 ,ord_no			,remark				,old_eq_no		");
    	query.append("	 ,serial_no			,as_vendor			,as_name		");
    	query.append("	 ,as_phone			,eq_grade       	,plant			");
    	query.append("	 ,eqctg_type    	,p_equip_id			,buy_date       ");
    	query.append("   ,disused_date 		,pmi_action_type 	,prod_shape 	");
    	query.append("   ,eqstrloc_no 		,supplier 			,country_maker  ");
    	query.append("	 ,ctctr_id	    	,eq_spec			,usage_dept		");
    	query.append("	 ,usage_emp			,upd_by				,upd_date		");
    	query.append("	 ,tag_no			,eq_size			,weight			");
    	query.append("   ,lnwrklist_id		,cre_by				,cre_date		");
    	query.append("   ,currency			,guaranty_date		,quantity		");
    	query.append("   ,run_date			,revisionhist_id   ,revision_status	");
    	query.append("   ,is_last_version	,is_deleted			,storage		");
    	query.append("	)														");
    	query.append("SELECT 													");
    	query.append("	 comp_no			,?					,?				");
    	query.append("	 ,description+'-Copyed'		,eqloc_id	,eqctg_id		");
    	query.append("	 ,eq_status			,dept_id			,main_mng_id	");
    	query.append("	 ,sub_mng_id		,setup_date			,buy_amt		");
    	query.append("	 ,plf_type			,maker				,model_no		");
    	query.append("	 ,capacity			,util_capa			,is_law_eq		");
    	query.append("	 ,ord_no			,remark				,old_eq_no		");
    	query.append("	 ,serial_no			,as_vendor			,as_name		");
    	query.append("	 ,as_phone			,eq_grade       	,plant			");
    	query.append("	 ,eqctg_type    	,p_equip_id			,buy_date       ");
    	query.append("   ,disused_date 		,pmi_action_type 	,prod_shape 	");
    	query.append("   ,eqstrloc_no 		,supplier 			,country_maker  ");
    	query.append("	 ,ctctr_id	    	,eq_spec			,usage_dept		");
    	query.append("	 ,usage_emp			,?			,CONVERT(VARCHAR,GETDATE(),112)+replace(CONVERT(VARCHAR,GETDATE(),108),':','')	");
    	query.append("	 ,tag_no			,eq_size			,weight			");
    	query.append("   ,lnwrklist_id		,?			,CONVERT(VARCHAR,GETDATE(),112)+replace(CONVERT(VARCHAR,GETDATE(),108),':','')	");
    	query.append("   ,currency			,guaranty_date		,quantity		");
    	query.append("   ,run_date			,?					,?				");
    	query.append("   ,?					,'N'				,storage		");
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
        
		return getJdbcTemplate().update(query.toString(), objects);
	}

	public String getNextSequence(String seqName)
    {
    	return super.getNextSequence(seqName);
    }

	public int copyEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQALTHIST								");
    	query.append("	(comp_no,		eqalthist_id,		upd_date,		");
    	query.append("	 upd_by,		equip_id,			item_no,		");
    	query.append("	 description,	eqloc_id,			eqctg_id,		");
    	query.append("	 eq_status,		dept_id,			main_mng_id,	");
    	query.append("	 sub_mng_id,	is_law_eq,			remark			");
    	query.append("	)	VALUES											");
    	query.append("	(?,				NEXT VALUE FOR SQAEQALTHIST_ID,CONVERT(VARCHAR, GETDATE(), 112) +' '+ CONVERT(VARCHAR, GETDATE(), 108),	");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?,			");
    	query.append("	 ?,					?,					?			");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			loginUser.getUserId(),
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
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int copyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAEQMOLD						");
		query.append("	( comp_no			,equip_id			");
		query.append("	 ,eqmold_atype		,eqmold_btype		");
		query.append("	 ,eqmold_ctype		,cavity				");
		query.append("	 ,storage			,setcnt				");
		query.append("	 ,output			,ownership			");
		query.append("	 ,use_period		,is_disuse			");
		query.append("	 ,disuse_amt		,disuse_date		");
		query.append("	 ,disuse_vendor		,stepno				");
		query.append("	)										");
		query.append("SELECT 									");
    	query.append("	  comp_no			,?					");
    	query.append("	 ,eqmold_atype		,eqmold_btype		");
		query.append("	 ,eqmold_ctype		,cavity				");
		query.append("	 ,storage			,setcnt				");
		query.append("	 ,output			,ownership			");
		query.append("	 ,use_period		,is_disuse			");
		query.append("	 ,disuse_amt		,disuse_date		");
		query.append("	 ,disuse_vendor		,stepno				");
        query.append("FROM TAEQMOLD								");
        query.append("WHERE comp_no		= ?						");
        query.append(" AND  equip_id	= ?						");
		
    	Object[] objects = new Object[] {
				  maEqMstrDetailDTO.getEquipId()
				, user.getCompNo()
				, maEqMstrDetailDTO.getOldEquipId()
    	};
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public int copyToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAEQTOOL											");
		query.append("	( comp_no			,equip_id			,guage_type			");
		query.append("	 ,measure_unit		,is_standard_eq		,base_equip_id		");
		query.append("	 ,measure_tool		,measure_categ		,min_unit_value		");
		query.append("	 ,min_value			,max_value			,pmc_type			");
		query.append("	 ,pmcalibstdtp_id	,all_range			,use_range			");
		query.append("	 ,accuracy			,tolerance								");
		query.append("	)															");
		query.append("SELECT 														");
    	query.append("	  comp_no			,?					,guage_type			");
		query.append("	 ,measure_unit		,is_standard_eq		,base_equip_id		");
		query.append("	 ,measure_tool		,measure_categ		,min_unit_value		");
		query.append("	 ,min_value			,max_value			,pmc_type			");
		query.append("	 ,pmcalibstdtp_id	,all_range			,use_range			");
		query.append("	 ,accuracy			,tolerance								");
        query.append("FROM TAEQTOOL													");
        query.append("WHERE comp_no		= ?											");
        query.append(" AND  equip_id	= ?											");

    	Object[] objects = new Object[] {
    			  maEqMstrDetailDTO.getEquipId()
    			, user.getCompNo()
    			, maEqMstrDetailDTO.getOldEquipId()
    	};
    	
		return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int copyBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQBUILDING						");
		query.append("	( comp_no		,equip_id		,addr		");
		query.append("	 ,area			,struct			,floor		");
		query.append("	 ,section									");
		query.append("	)											");
		query.append("SELECT 										");
    	query.append("	  comp_no		,?				,addr		");
		query.append("	 ,area			,struct			,floor		");
		query.append("	 ,section									");
        query.append("FROM TAEQBUILDING								");
        query.append("WHERE comp_no		= ?							");
        query.append(" AND  equip_id	= ?							");

    	Object[] objects = new Object[] {
    			  maEqMstrDetailDTO.getEquipId()
    			, user.getCompNo()
    			, maEqMstrDetailDTO.getOldEquipId()
    	};
    	
		return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int copyDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQDEVICE				");
		query.append("	( comp_no		,equip_id			");
		query.append("	 ,part_id		,wcode_id			");
		query.append("	)									");
		query.append("SELECT 								");
    	query.append("	  comp_no		,?					");
		query.append("	 ,part_id		,wcode_id			");
        query.append("FROM TAEQDEVICE						");
        query.append("WHERE comp_no		= ?					");
        query.append(" AND  equip_id	= ?					");
    	
    	
    	Object[] objects = new Object[] {
    			  maEqMstrDetailDTO.getEquipId()
    			, loginUser.getCompNo()
    			, maEqMstrDetailDTO.getOldEquipId()
    	};
		return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int copyITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQIT							");
		query.append("	( comp_no		,equip_id		,ipaddr		");
		query.append("	 ,os_name		,hostname		,mac_addr	");
		query.append("	)											");
		query.append("SELECT 										");
    	query.append("	  comp_no		,?				,ipaddr		");
		query.append("	 ,os_name		,hostname		,mac_addr	");
        query.append("FROM TAEQIT									");
        query.append("WHERE comp_no		= ?							");
        query.append(" AND  equip_id	= ?							");
    	
    	Object[] objects = new Object[] {
     			  maEqMstrDetailDTO.getEquipId()
    			, loginUser.getCompNo()
    			, maEqMstrDetailDTO.getOldEquipId()
    	};
		return  this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

    @Override
    public String validItemNo(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAEQUIPMENT x                   ");
        query.append("WHERE 1=1 ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.item_no", maEqMstrDetailDTO.getItemNo());
        query.append("AND x.is_deleted = 'N'                 ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

    @Override
    public void updateStatus(String equipId, String compNo, String eqStatusId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEQUIPMENT SET                            ");
        query.append("  eq_status       = ?                             ");
        query.append("WHERE equip_id    = ?                             ");
        query.append("  AND comp_no     = ?                             ");
        
        Object[] objects = new Object[] {
                 eqStatusId,
                 equipId,
                 compNo
        };

        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public void updRevObjectNo(String itemNo, MaEqMstrDetailDTO maEqMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAREVISIONHIST SET		");
        query.append("  	 object_no		= ?     ");
        query.append(" WHERE object_no    	= ?     ");
        query.append("   AND comp_no     	= ?     ");
        
        Object[] objects = new Object[] {
        		 maEqMstrDetailDTO.getItemNo()
        	   , itemNo
        	   , user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public void updEqHistItemNo(String itemNo, MaEqMstrDetailDTO maEqMstrDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEQHISTORY SET		");
        query.append("  	 item_no		= ?     ");
        query.append(" WHERE item_no    	= ?     ");
        query.append("   AND comp_no     	= ?     ");
        
        Object[] objects = new Object[] {
        		 maEqMstrDetailDTO.getItemNo()
        	   , itemNo
        	   , user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public void updEqItemNo(String itemNo, MaEqMstrDetailDTO maEqMstrDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEQUIPMENT SET		");
        query.append("  	 item_no		= ?     ");
        query.append(" WHERE item_no    	= ?     ");
        query.append("   AND comp_no     	= ?     ");
        
        Object[] objects = new Object[] {
        		 maEqMstrDetailDTO.getItemNo()
        	   , itemNo
        	   , user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
}