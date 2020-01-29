package dream.work.close.check.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.close.check.dao.MgrWorkCloseCheckDetailDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckDetailDTO;

/**
 * MgrWorkCloseCheck Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkCloseCheckDetailDAOTarget"
 * @spring.txbn id="mgrWorkCloseCheckDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkCloseCheckDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrWorkCloseCheckDetailDAO
{
    public MgrWorkCloseCheckDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                     	");
        query.append("      ''                                 ISDELCHECK           ");
        query.append("    , ''                                 SEQNO                ");
        query.append("    , x.stwrk_id                         stwrkId              ");
        query.append("    , x.stwrk_no                         stwrkNo             	");
        query.append("    , x.stwrk_status                     stwrkStatus   		");
        query.append("    , x.description                      stwrkDesc            ");
        query.append("    , (SELECT SFACODE_TO_DESC(x.stwrk_status, 'STWRK_STATUS', 'SYS', x.comp_no, ?) FROM DUAL ) stwrkStatusDesc		");
        query.append("    , y.wo_type                          woType        		");
        query.append("    , (SELECT SFACODE_TO_DESC(y.wo_type , 'WO_TYPE', 'SYS', x.comp_no, ?) FROM DUAL ) woTypeDesc		");
        query.append("    , y.pm_type                          pmType        		");
        query.append("    , (SELECT SFACODE_TO_DESC(y.pm_type , y.wo_type||'_TYPE', 'SYS', x.comp_no, ?) FROM DUAL ) pmTypeDesc		");
        query.append("    , x.plant							   plant         		");
        query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant) plantDesc		");
        query.append("    , x.is_active 					   isActive        		");
        query.append("    , (SELECT SFACODE_TO_DESC(x.is_active , 'IS_USE', 'SYS', x.comp_no, ?) FROM DUAL )  isActiveDesc		");
        query.append("    , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) regDeptDesc		");
        query.append("    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.reg_by) regByDesc		");
        query.append("    , TO_CHAR(TO_DATE(x.reg_date,'yyyy-mm-dd'),'yyyy-mm-dd') regDate        		");
        query.append("    , x.remark                           remark               ");
        query.append("	  , y.stwrkwork_id					   stwrkWorkId			");
        query.append("FROM TASTWRKLST x INNER JOIN TASTWRKWORK y                	");
        query.append("ON x.comp_no = y.Comp_no AND x.stwrk_id = y.stwrk_id			");
        query.append("WHERE  1=1                                                	");
        query.append("  AND x.comp_no    	    = ?                             	");
        query.append("  AND x.stwrk_id		    = ?                             	");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		, user.getLangId()
        		, user.getLangId()
        		, user.getLangId()
                , user.getCompNo()
                , mgrWorkCloseCheckCommonDTO.getStwrkId()
        };
    
        MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO = 
                (MgrWorkCloseCheckDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrWorkCloseCheckDetailDTO()));
        
        return mgrWorkCloseCheckDetailDTO;
        
    }
    
    public int insertDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

    	query.append("INSERT INTO TASTWRKLST                    ");
    	query.append("    (comp_no			, stwrk_id			");
    	query.append("    , stwrk_no        , description		");
    	query.append("    , stwrk_status    , dept_id			");
    	query.append("    , reg_date        , reg_by			");
    	query.append("    , upd_date        , upd_by			");
    	query.append("    , plant           , remark			");
    	query.append("    , stwrk_type      , is_active			");
    	query.append("    , is_last_version	, revisionhist_id	");
    	query.append("    , revision_status						");
    	query.append(") VALUES                           		");
    	query.append("    (?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                                    ");
    	query.append(")                                     	");
    	
    	Object[] objects = new Object[] 
		{
			user.getCompNo()	
			, mgrWorkCloseCheckDetailDTO.getStwrkId()
			, mgrWorkCloseCheckDetailDTO.getStwrkNo()
			, mgrWorkCloseCheckDetailDTO.getStwrkDesc()
			, mgrWorkCloseCheckDetailDTO.getStwrkStatus()
			, mgrWorkCloseCheckDetailDTO.getRegDept()
			, mgrWorkCloseCheckDetailDTO.getRegDate()
			, mgrWorkCloseCheckDetailDTO.getRegBy()
			, mgrWorkCloseCheckDetailDTO.getUpdDate()
			, mgrWorkCloseCheckDetailDTO.getUpdBy()
			, mgrWorkCloseCheckDetailDTO.getPlant()
			, mgrWorkCloseCheckDetailDTO.getRemark()
			, "W"
			, mgrWorkCloseCheckDetailDTO.getIsActive()
			, mgrWorkCloseCheckDetailDTO.getIsLastVersion()
			, mgrWorkCloseCheckDetailDTO.getRevisionhistId()
			, mgrWorkCloseCheckDetailDTO.getRevisionStatusId()
    	};

        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int insertWorkDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TASTWRKWORK                   ");
    	query.append("    (comp_no			, stwrk_id			");
    	query.append("    , stwrkwork_id    , wo_type			");
    	query.append("    , pm_type		    , description		");
    	query.append("    , remark								");
    	query.append(") VALUES                           		");
    	query.append("    (?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                ,?                  ");
    	query.append("    ,?                                    ");
    	query.append(")                                     	");
    	
    	Object[] objects = new Object[] 
		{
			user.getCompNo()	
			, mgrWorkCloseCheckDetailDTO.getStwrkId()
			, mgrWorkCloseCheckDetailDTO.getStwrkWorkId()
			, mgrWorkCloseCheckDetailDTO.getWoType()
			, mgrWorkCloseCheckDetailDTO.getPmType()
			, mgrWorkCloseCheckDetailDTO.getStwrkDesc()
			, mgrWorkCloseCheckDetailDTO.getRemark()
		};

    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TASTWRKLST  SET  	");
    	query.append("      description     = ? ");
    	query.append("    , upd_date    	= ? ");
    	query.append("    , upd_by    		= ? ");
    	query.append("    , plant    		= ? ");
    	query.append("    , is_active    	= ? ");
    	query.append("    , remark    		= ? ");
    	query.append("WHERE stwrk_id		= ? ");
    	query.append("  AND comp_no         = ? ");
    	
    	Object[] objects = new Object[] 
		{
			mgrWorkCloseCheckDetailDTO.getStwrkDesc()
			,mgrWorkCloseCheckDetailDTO.getUpdDate()
			,mgrWorkCloseCheckDetailDTO.getUpdBy()
			,mgrWorkCloseCheckDetailDTO.getPlant()
			,mgrWorkCloseCheckDetailDTO.getIsActive()
			,mgrWorkCloseCheckDetailDTO.getRemark()
			,mgrWorkCloseCheckDetailDTO.getStwrkId()
			,user.getCompNo()
		};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateWorkDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TASTWRKWORK  SET  	");
    	query.append("      description     = ? ");
    	query.append("    , wo_type    		= ? ");
    	query.append("    , pm_type    		= ? ");
    	query.append("    , remark    		= ? ");
    	query.append("WHERE stwrkwork_id	= ? ");
    	query.append("  AND comp_no         = ? ");
    	
    	Object[] objects = new Object[] 
		{
			mgrWorkCloseCheckDetailDTO.getStwrkDesc()
			,mgrWorkCloseCheckDetailDTO.getWoType()
			,mgrWorkCloseCheckDetailDTO.getPmType()
			,mgrWorkCloseCheckDetailDTO.getRemark()
			,mgrWorkCloseCheckDetailDTO.getStwrkWorkId()
			,user.getCompNo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    

    public int confirmDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKLST			");
    	query.append("SET stwrk_status = ?		");
    	query.append("	 ,revision_status = ?	");
    	query.append("	 ,is_last_version = ?	");
    	query.append("WHERE comp_no = ?			");
    	query.append("AND stwrk_id  = ?			");
    	
    	objects = new Object[] {
    			"C"
    			,"C"
    			,"Y"
    			,user.getCompNo()
    			,mgrWorkCloseCheckDetailDTO.getStwrkId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
	public int insertRevisionHist(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user, String histId) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAREVISIONHIST               				");
    	query.append("(                                        				");
    	query.append("  revisionhist_id,			comp_no,            	");
    	query.append("  object_id,  				object_no,       		");
    	query.append("	revision_object_type,      	doc_no,                 ");
    	query.append("  revision_status,         	revision_step_status,	");
    	query.append("  revision_type,           	rev_no,        			");
    	query.append("  wrk_date,           		wrk_id,         		");
    	query.append("  rev_desc       				   						");
    	query.append(")VALUES                                  				");
    	query.append("(                                        				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                    			");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?                                   				");
    	query.append(")                                        				");

    	
    	Object[] objects = new Object[] {
    			histId
    			,user.getCompNo()
    	        ,mgrWorkCloseCheckDetailDTO.getStwrkId()
    	        ,mgrWorkCloseCheckDetailDTO.getStwrkNo()
    	        ,"STWRK"
    	        ,""
    	        ,"C"
    	        ,"CMP"
    	        ,"C"
    	        ,"1.00"
    	        ,DateUtil.getDateTime("yyyyMMdd")
    	        ,user.getEmpId()
    	        ,mgrWorkCloseCheckDetailDTO.getStwrkDesc()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
    

	@Override
	public int updateStdPointMstrLastVersion(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user, String histId) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TASTWRKLST SET                					");
    	query.append("		revisionhist_id = ?                             ");
    	query.append("		,revision_status = ?                            ");
    	query.append("		,is_last_version = ?                            ");
    	query.append("WHERE comp_no = ?                       				");
    	query.append("AND   stwrk_id = ?                       				");

    	
    	Object[] objects = new Object[] {
    			histId
    			,"C"
    	        ,"Y"
    	        ,user.getCompNo()
    	        ,mgrWorkCloseCheckDetailDTO.getStwrkId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
}

