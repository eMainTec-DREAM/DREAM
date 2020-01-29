package dream.asset.loc.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.loc.list.dao.MaEqLocDetailDAO;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;

/**
 * 설비위치 - 상세
 * 
 * @author kim21017
 * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maEqLocDetailDAOTarget"
 * @spring.txbn id="maEqLocDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqLocDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqLocDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     */
    public MaEqLocDetailDTO findDetail(String eqLocId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																				");
        query.append("		x.eqloc_id													AS eqLocId,			");
        query.append("		x.eqloc_no													AS eqLocNo,			");
        query.append("		CASE x.p_eqloc_id WHEN '0' THEN '' ELSE x.p_eqloc_id END	AS pEqLocId,		");
        query.append("		(SELECT full_desc																");
        query.append("		   FROM TAEQLOC 																");
        query.append("		  WHERE eqloc_id = x.p_eqloc_id													");
        query.append("		    AND comp_no  = x.comp_no)								AS pEqLocDesc,		");
        query.append("		ctctr_id 													AS ctCtrId,			");
        query.append("		(SELECT description																");
        query.append("		   FROM TACTCTR 																");
        query.append("		  WHERE ctctr_id = x.ctctr_id													");
        query.append("		    AND comp_no  = x.comp_no)								AS ctctrDesc,		");
        query.append("      wkctr_id                                                    AS wkCtrId,         ");
        query.append("      (SELECT description                                                             ");
        query.append("         FROM TAWKCTR                                                                 ");
        query.append("        WHERE wkctr_id = x.wkctr_id                                                   ");
        query.append("          AND comp_no  = x.comp_no)                               AS wkctrDesc,       ");
        query.append("		mes_line_id 												AS mesLineId,		");
        query.append("		(SELECT mes_line_name															");
        query.append("		   FROM TAMESLINE 																");
        query.append("		  WHERE mes_line_id = x.mes_line_id												");
        query.append("		    AND comp_no  = x.comp_no)								AS mesLineDesc,		");
        query.append("		x.description												AS eqLocDesc,		");
        query.append("		x.eqloc_lvl_type											AS levelType,		");
        query.append("		dbo.SFACODE_TO_DESC(x.eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+user.getLangId()+"')	AS levelTypeDesc,	");
        query.append("		x.plant														AS plant,			");
        query.append("		dbo.SFAPLANTTODESC(x.comp_no, x.plant)							AS plantDesc,		");
        query.append("       x.ord_no													AS ordNo,			");
        query.append("       x.is_use													AS isUse,			");
        query.append("       x.is_operation										    	AS isOperation,   	");
        query.append("       x.is_kpi													AS isKpi,			");
        query.append("       x.is_bd_loc												AS isBdLoc,			");
        query.append("       x.bd_equip_id												AS bdEquipId,		");
        query.append("       x.dtime													AS dayRunTime,		");
        query.append("       x.ntime													AS nightRunTime,	");
        query.append("       x.etime													AS extraRunTime,	");
        query.append("       x.mng_cd									                AS mngCd,		    ");
        query.append("       x.remark													AS remark,			");
        query.append("       x.lnwrklist_id                                       		AS lnWrkListId,		");
        query.append("       (SELECT aa.description 														");
        query.append("       FROM TALNWRKLIST aa 															");
        query.append("       WHERE x.comp_no = aa.comp_no 													");
        query.append("       AND x.lnwrklist_id = aa.lnwrklist_id) 						AS lnWrkListDesc,	");
        query.append("      (SELECT COUNT(*)                                                				");
        query.append("       FROM TAEQUIPMENT a                                      				      	");
        query.append("       WHERE a.comp_no = x.comp_no                              				     	");
        query.append("        AND a.is_deleted = 'N'         	                           					");
        query.append("        AND a.is_last_version = 'Y'  	                             					");
        query.append("        AND a.eqloc_id IN (SELECT a.eqloc_id  	                            		");
        query.append("        					 FROM (SELECT * FROM dbo.SFAEQLOC_CALL(x.comp_no,x.eqloc_id,'')) a	");
        query.append("           				 WHERE a.comp_no = x.comp_no ))			AS eqCnt			");
        query.append("FROM   TAEQLOC x																		");
        query.append("WHERE  1=1																			");
        query.append("  AND  x.eqloc_id = ?																	");
        query.append("  AND  x.comp_no  = ?																	");

        Object[] objects = new Object[] {
        		eqLocId,
        		user.getCompNo()
    	};
        
        MaEqLocDetailDTO maEqLocDetailDTO = 
        		(MaEqLocDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqLocDetailDTO()));
        
        return maEqLocDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailDTO
     * @return
     */
    public int insertDetail(MaEqLocDetailDTO maEqLocDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String pEqLocId = maEqLocDetailDTO.getPeqLocId();
    	if("".equals(pEqLocId)){
    		pEqLocId = "0";
    	}

    	query.append("INSERT INTO TAEQLOC									");
    	query.append("	(comp_no,		eqloc_id,			eqloc_no,		");
    	query.append("	 description,	p_eqloc_id,			eqloc_lvl_type,	");
    	query.append("	 ord_no,		is_use,				plant,			");
    	query.append("	 ctctr_id,		mes_line_id,        is_operation,	");
    	query.append("	 is_kpi,        remark,dtime,ntime,etime           	");
    	query.append("	 ,is_bd_loc, 	mng_cd,				lnwrklist_id    ");
    	query.append("   ,wkctr_id                                          ");
    	query.append("	)	VALUES											");
    	query.append("	(?,				?,					?,				");
    	query.append("	 ?,				?,					?,				");
    	query.append("	 ?,				?,					?,				");
    	query.append("	 ?,				?,                  ?,				");
    	query.append("	 ?,             ?, ?,?,?               				");
    	query.append("	 ,?, 			?,					?               ");
    	query.append("   ,?                                                 ");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			maEqLocDetailDTO.getCompNo()
    			,maEqLocDetailDTO.getEqLocId()
    			,maEqLocDetailDTO.getEqLocNo()
    			,maEqLocDetailDTO.getEqLocDesc()
    			,pEqLocId
    			,maEqLocDetailDTO.getLevelType()
    			,maEqLocDetailDTO.getOrdNo()
    			,maEqLocDetailDTO.getIsUse()
    			,maEqLocDetailDTO.getPlant()
    			,maEqLocDetailDTO.getCtCtrId()
    			,maEqLocDetailDTO.getMesLineId()
    			,maEqLocDetailDTO.getIsOperation()
    			,maEqLocDetailDTO.getIsKpi()
    			,maEqLocDetailDTO.getRemark()
    			,maEqLocDetailDTO.getDayRunTime()
    			,maEqLocDetailDTO.getNightRunTime()
    			,maEqLocDetailDTO.getExtraRunTime()
    			,maEqLocDetailDTO.getIsBdLoc()
    			,maEqLocDetailDTO.getMngCd()
    			,maEqLocDetailDTO.getLnWrkListId()
    			,maEqLocDetailDTO.getWkCtrId()
    	};
    	rtnValue = this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    
    public int insertBuildingEquipment(MaEqLocDetailDTO maEqLocDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	
    	String equipId = getNextSequence("SQAEQUIP_ID");
    	String revisionHistId = getNextSequence("SQAREVISIONHIST_ID");
    	
    	query.append("            insert into TAREVISIONHIST(                                 ");
    	query.append("                   comp_no, revisionhist_id, revision_object_type       ");
    	query.append("                  , object_id, object_no                                ");
    	query.append("                  , revision_status,revision_step_status, revision_type ");
    	query.append("                  , wrk_date,rev_desc                                   ");
    	query.append("                  , wrk_id                                              ");
    	query.append("           )values(                                                     ");
    	query.append("                   ?, ?, ?                                              ");
    	query.append("                  ,?, ?                                                 ");
    	query.append("                  ,?, ?, ?                                              ");
    	query.append("                  ,convert(varchar(8), getdate(), 112),'created in Location' ");
    	query.append("                  ,?                                                    ");
    	query.append("            )                                                           ");
    	Object[] objects = new Object[] {
    			maEqLocDetailDTO.getCompNo()
    			,revisionHistId
    			,"ASSET"
    			,equipId
    			,equipId
    			,"C"
    			,"CMP"
    			,"C"
    			,maEqLocDetailDTO.getEnterBy()
    			
    	};
    	rtnValue = this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	query = new QuerySqlBuffer();
    	query.append("insert into TAEQUIPMENT(                                                ");
    	query.append("     comp_no, equip_id, item_no, description, eqloc_id, eq_status       ");
    	query.append("    ,eqctg_type, is_last_version, revision_status, revisionhist_id      ");
    	query.append("    ,plant															  ");
    	query.append(" )                                                                      ");
    	query.append("select  comp_no, ?, ?, description, eqloc_id, 'R'                       ");
    	query.append("    ,'BD', 'Y', 'C' ,?                                                   ");
    	query.append("	  ,plant															  ");
    	query.append("from taeqloc                                                            ");
    	query.append("where comp_no = ?                                                       ");
    	query.append("    and eqloc_id = ?                                                    ");
    	
    	objects = new Object[] {
    			 equipId
    			,equipId
    			,revisionHistId
    			,maEqLocDetailDTO.getCompNo()
    			,maEqLocDetailDTO.getEqLocId()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	query = new QuerySqlBuffer();
    	query.append("insert into TAEQBUILDING(                                               ");
    	query.append("     comp_no, equip_id                                                  ");
    	query.append(" ) values (                                                             ");
    	query.append("    ?, ?                                                                ");
    	query.append(" )                                                                      ");
    	
    	objects = new Object[] {
    			   maEqLocDetailDTO.getCompNo()
    			 ,equipId
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	
    	query = new QuerySqlBuffer();
    	query.append("update TAEQLOC set BD_EQUIP_ID = ?                                      ");
    	query.append("where comp_no = ?                                                       ");
    	query.append("    and eqloc_id = ?                                                    ");
    	
    	objects = new Object[] {
    			 equipId
    			,maEqLocDetailDTO.getCompNo()
    			,maEqLocDetailDTO.getEqLocId()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), getObject(objects));

    	return rtnValue;
    }
    
    
    public int insertLevelNDetail(MaEqLocDetailDTO maEqLocDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAEQLOC set IS_LOWEST_LVL = 'N'                ");
    	query.append("WHERE 1=1                                             ");
    	query.append("    AND comp_no = '"+maEqLocDetailDTO.getCompNo()+"'	");
    	query.append("    AND eqloc_id in (                         		");
    	query.append("                     SELECT eqloc_id FROM dbo.SFAEQLOC_CALL('"+maEqLocDetailDTO.getCompNo()+"','"+maEqLocDetailDTO.getEqLocId()+"','')	");
    	query.append("                    )                               	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());    
        return rtnValue;
    }
    
    public int insertLevelYDetail(MaEqLocDetailDTO maEqLocDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	//만약에 해다 값이 없다면
        query = new QuerySqlBuffer();
        
        query.append("SELECT eqloc_id                                            ");
        query.append("FROM   dbo.SFAEQLOC_ALL('"+maEqLocDetailDTO.getCompNo()+"','"+maEqLocDetailDTO.getEqLocId()+"')	");
        
        if (!QuerySqlBuffer.haveData(getJdbcTemplate().queryForList(query.toString()))) {
        	
        	query = new QuerySqlBuffer();
        	query.append("UPDATE TAEQLOC SET IS_LOWEST_LVL = 'Y'             	");
        	query.append("WHERE 1=1                                            	");
        	query.append("    AND comp_no = '"+maEqLocDetailDTO.getCompNo()+"' 	");
        	query.append("    AND eqloc_id = "+maEqLocDetailDTO.getEqLocId()+ "	");
        	rtnValue = this.getJdbcTemplate().update(query.toString());
        }
        return rtnValue;
    }
    
    public int insertFullDescDetail(MaEqLocDetailDTO maEqLocDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
        query.append("EXEC dbo.SP_EQ_UPD_EQLOC '"+maEqLocDetailDTO.getCompNo()+"',"+maEqLocDetailDTO.getEqLocId()+"     ");
    	
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailDTO
     * @return
     */
    public int updateDetail(MaEqLocDetailDTO maEqLocDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	String pEqLocId = maEqLocDetailDTO.getPeqLocId();
    	if("".equals(pEqLocId)){
    		pEqLocId = "0";
    	}
    	
    	query.append("UPDATE TAEQLOC SET		");
    	query.append("	eqloc_no	= ?,		");
    	query.append("	description	= ?,		");
    	query.append("	p_eqloc_id	= ?,		");
    	query.append("	ord_no		= ?,		");
    	query.append("	is_use		= ?,		");
    	query.append("	ctctr_id	= ?,		");
    	query.append("	wkctr_id	= ?,		");
    	query.append("	mes_line_id	= ?,		");
    	query.append("	plant		= ?,		");
    	query.append("	eqloc_lvl_type	= ?,	");
    	query.append("	is_operation	= ?,	");
    	query.append("	dtime		= ?,	    ");
    	query.append("	ntime		= ?,	    ");
    	query.append("	etime		= ?,	    ");
    	query.append("	is_kpi		= ?,	    ");
    	query.append("	mng_cd		= ?,		");
    	query.append("	remark		= ?,		");
    	query.append("	lnwrklist_id	= ?		");
    	query.append("WHERE eqloc_id = ?		");
    	query.append("  AND comp_no  = ?		");
    	
    	Object[] objects = new Object[] {
    			maEqLocDetailDTO.getEqLocNo(),
    			maEqLocDetailDTO.getEqLocDesc(),
    			pEqLocId,
    			maEqLocDetailDTO.getOrdNo(),
    			maEqLocDetailDTO.getIsUse(),
    			maEqLocDetailDTO.getCtCtrId(),
    			maEqLocDetailDTO.getWkCtrId(),
    			maEqLocDetailDTO.getMesLineId(),
    			maEqLocDetailDTO.getPlant(),
    			maEqLocDetailDTO.getLevelType(),
    			maEqLocDetailDTO.getIsOperation(),
    			maEqLocDetailDTO.getDayRunTime(),
    			maEqLocDetailDTO.getNightRunTime(),
    			maEqLocDetailDTO.getExtraRunTime(),
    			maEqLocDetailDTO.getIsKpi(),
    			maEqLocDetailDTO.getMngCd(),
    			maEqLocDetailDTO.getRemark(),
    			maEqLocDetailDTO.getLnWrkListId(),
    			maEqLocDetailDTO.getEqLocId(),
    			maEqLocDetailDTO.getCompNo()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public int updateBuildingEquipment(MaEqLocDetailDTO maEqLocDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("update TAEQUIPMENT set description = (select description                ");
    	query.append("                                      from taeqloc                      ");
    	query.append("                                      where comp_no = ?                 ");
    	query.append("                                            and eqloc_id = ?            ");
    	query.append("                                     )                                  ");
    	query.append("where comp_no = ?                                                       ");
    	query.append("    and equip_id = ?                                                    ");
    	
    	Object[] objects = new Object[] {
    			 maEqLocDetailDTO.getCompNo()
    			,maEqLocDetailDTO.getEqLocId()
    			,maEqLocDetailDTO.getCompNo()
    			,maEqLocDetailDTO.getBdEquipId()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int updateRunTime(MaEqLocDetailDTO maEqLocDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TALNWRKTIME		");
    	query.append("WHERE 1=1						");
    	query.append("  AND wrk_date >= CONVERT(VARCHAR, GETDATE(), 112)	");
    	query.getAndNumKeyQuery("eqloc_id", maEqLocDetailDTO.getEqLocId());
    	query.getStringEqualQuery("comp_no", maEqLocDetailDTO.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_MAKE_TALNWRKTIME '"+maEqLocDetailDTO.getCompNo()+"','"+maEqLocDetailDTO.getEqLocId()+"'; ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return rtnValue;
    }
    
    public String findEqLocId(String eqLocNo, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT x.eqloc_id		");
        query.append("	FROM TAEQLOC x		");
        query.append(" WHERE 1 = 1			");
        query.append("	 AND x.comp_no  = ?	");
        query.append("	 AND x.eqloc_no = ?	");

        Object[] objects = new Object[] {
        		 user.getCompNo()
        	   , eqLocNo
    	};
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
}