package dream.consult.comp.time.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.time.dao.MaLineTimeDowDetailDAO;
import dream.consult.comp.time.dto.MaLineTimeDowDetailDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별 설정 상세 dao
 * @author  kim21017
 * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maLineTimeDowDetailDAOTarget"
 * @spring.txbn id="maLineTimeDowDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLineTimeDowDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaLineTimeDowDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param eqLocDowRunId
     * @param compNo
     * @return
     */
    public MaLineTimeDowDetailDTO findDetail(MaLineTimeDowListDTO maLineTimeDowListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT													");
        query.append("       x.lnwrklist_id			lnWrkListId					                        ");
        query.append("       ,x.comp_no         compNo                                                  ");
        query.append("       ,x.eqlocdowrun_id	eqLocDowRunId					                        ");
        query.append("       ,x.dow				dow							                            ");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+user.getLangId()+"') dowDesc	");
        query.append("       ,x.dtime			dayRunTime						                        ");
        query.append("       ,x.ntime			nightRunTime					                        ");
        query.append("       ,x.etime			extraRunTime					                        ");
        query.append("       ,x.is_use			isUse							                        ");
        query.append("       ,x.ucnt			ucnt							                        ");
        query.append("       ,x.ord_no			ordNo							                        ");
        query.append("FROM   TAEQLOCDOWRUN x									");
        query.append("WHERE	 1=1												");
        query.append("  AND	 x.eqlocdowrun_id 	= ?								");
        query.append("  AND  x.comp_no			= ?								");
    
        Object[] objects = new Object[] {
                maLineTimeDowListDTO.getEqLocDowRunId(),
                maLineTimeDowListDTO.getCompNo()
    	};
        MaLineTimeDowDetailDTO maLineTimeDowDetailDTO = 
        		(MaLineTimeDowDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaLineTimeDowDetailDTO()));
        
        return maLineTimeDowDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailDTO
     * @param maLineTimeCommonDTO
     * @return
     */
    public int updateDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQLOCDOWRUN SET		");
    	query.append("	dtime				= ?	    ");
    	query.append("	,ntime				= ?	    ");
    	query.append("	,etime				= ?	    ");
    	query.append("	,is_use				= ?	    ");
    	query.append("	,ord_no				= ?		");
    	query.append("	,ucnt				= ?		");
    	query.append("WHERE eqlocdowrun_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			maLineTimeDowDetailDTO.getDayRunTime()
    			,maLineTimeDowDetailDTO.getNightRunTime()
    			,maLineTimeDowDetailDTO.getExtraRunTime()
    			,maLineTimeDowDetailDTO.getIsUse()
    			,maLineTimeDowDetailDTO.getOrdNo()
    			,maLineTimeDowDetailDTO.getUcnt()
    			,maLineTimeDowDetailDTO.getEqLocDowRunId()
    			,maLineTimeDowDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailDTO
     * @param maLineTimeCommonDTO
     * @return
     */
    public int insertDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQLOCDOWRUN				");
    	query.append("	(comp_no,		eqlocdowrun_id,		");
    	query.append("	 lnwrklist_id,		dow,			");
    	query.append("	 dtime,			ntime,				");
    	query.append("	 etime,			is_use,				");
    	query.append("	 ord_no, ucnt    					");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             ?					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maLineTimeDowDetailDTO.getCompNo()
    			,maLineTimeDowDetailDTO.getEqLocDowRunId()
    			,maLineTimeDowDetailDTO.getLnWrkListId()
    			,maLineTimeDowDetailDTO.getDow()
    			,maLineTimeDowDetailDTO.getDayRunTime()
    			,maLineTimeDowDetailDTO.getNightRunTime()
    			,maLineTimeDowDetailDTO.getExtraRunTime()
    			,maLineTimeDowDetailDTO.getIsUse()
    			,maLineTimeDowDetailDTO.getOrdNo()
    			,maLineTimeDowDetailDTO.getUcnt()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * 요일 중복 검사
     */
    public String validDay(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAEQLOCDOWRUN			");
    	query.append("WHERE 1=1						");
    	query.getStringEqualQuery("comp_no", maLineTimeDowDetailDTO.getCompNo());
    	query.getStringEqualQuery("dow", maLineTimeDowDetailDTO.getDow());
    	query.getAndNumKeyQuery("lnwrklist_id", maLineTimeDowDetailDTO.getLnWrkListId());

    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public int deleteWrkTime(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TALNWRKTIME							");
    	query.append("WHERE 1=1											");
    	query.append("  AND wrk_date >= CONVERT(VARCHAR, GETDATE(), 112)	");
    	query.getAndNumKeyQuery("lnwrklist_id", maLineTimeDowDetailDTO.getLnWrkListId());
    	query.getStringEqualQuery("comp_no", maLineTimeDowDetailDTO.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    public int execRunTime(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_MAKE_TALNWRKTIME '"+maLineTimeDowDetailDTO.getCompNo()+"','"+maLineTimeDowDetailDTO.getLnWrkListId()+"'; ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return rtnValue;
        
    }
}