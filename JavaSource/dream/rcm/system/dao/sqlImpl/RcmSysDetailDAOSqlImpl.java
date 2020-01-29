package dream.rcm.system.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysDetailDAO;
import dream.rcm.system.dto.RcmSysDetailDTO;

/**
 * System분석 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="rcmSysDetailDAOTarget"
 * @spring.txbn id="rcmSysDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public RcmSysDetailDTO findDetail(User user, String id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT								                    		");
        query.append("       x.comp_no                              compNo,				");
        query.append("       x.rcmlist_id		                    rcmListId,	    	");
        query.append("       x.rcmlist_no		                    rcmListNo,	    	");
        query.append("       x.description                          description,		");
        query.append("       x.rcmlist_status                       rcmListStatus,		");
        query.append("       dbo.SFACODE_TO_DESC(x.rcmlist_status, 'RCMLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') rcmListStatusDesc,	");
        query.append("       x.eqloc_id                             eqLocId,    		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)			eqLocDesc,			");
        query.append("       x.rcm_categ                            rcmCateg,    		");
        query.append("       dbo.SFACODE_TO_DESC(x.rcm_categ, 'RCM_CATEG', 'USR', x.comp_no,'')     rcmCategDesc,  ");
        query.append("       x.critylist_id	                        crityListId,   		");
        query.append("		(SELECT description											");
        query.append("		   FROM TACRITYLIST											");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND critylist_id = x.critylist_id)	crityListDesc,		");
        query.append("       x.pmtaskmaplist_id	                    pmTaskMapListId,   	");
        query.append("		(SELECT description											");
        query.append("		   FROM TAPMTASKMAPLIST										");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND pmtaskmaplist_id = x.pmtaskmaplist_id)	pmTaskMapListDesc,	");
        query.append("       x.start_date	                        startDate,   		");
        query.append("       x.end_date	                            endDate,   			");
        query.append("       x.reg_date	                            regDate,   			");
        query.append("       x.remark   	                        remark	    		");
        query.append("FROM   TARCMLIST x						                    	");
        query.append("WHERE  x.comp_no = '"+user.getCompNo()+"'							");
        query.append("  AND  x.rcmlist_id  = '"+id+"'									");
    
        RcmSysDetailDTO rcmSysDetailDTO = 
        		(RcmSysDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmSysDetailDTO()));
        
        return rcmSysDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysDetailDTO
     * @return
     */
    public int insertDetail(User user, RcmSysDetailDTO rcmSysDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TARCMLIST(					                    ");
    	query.append("   comp_no,			rcmlist_id, rcmlist_no,  description,	");
    	query.append("	 rcmlist_status,  	eqloc_id,	rcm_categ,   critylist_id,	");
    	query.append("	 pmtaskmaplist_id, 	start_date,	end_date,	 reg_date,      ");
    	query.append("	 remark									            		");
    	query.append(")VALUES(							                        	");
    	query.append("	 ?,			?,			?,	         ?,                 	");
    	query.append("	 ?,			?,			?,	         ?,                 	");
    	query.append("	 ?,			?,           ?,          ?,                 	");
    	query.append("	 ?										                	");
    	query.append(")													        	");
    	
    	Object[] objects = new Object[] {
    	        user.getCompNo(),
    			rcmSysDetailDTO.getRcmListId(),
    			rcmSysDetailDTO.getRcmListNo(),
    			rcmSysDetailDTO.getDescription(),
    			rcmSysDetailDTO.getRcmListStatus(),
    			rcmSysDetailDTO.getEqLocId(),
    			rcmSysDetailDTO.getRcmCateg(),
    			rcmSysDetailDTO.getCrityListId(),
    			rcmSysDetailDTO.getPmTaskMapListId(),
    			rcmSysDetailDTO.getStartDate(),
    			rcmSysDetailDTO.getEndDate(),
    			rcmSysDetailDTO.getRegDate(),
    			rcmSysDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysDetailDTO
     * @return
     */
    public int updateDetail(User user, RcmSysDetailDTO rcmSysDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMLIST SET	        ");
    	query.append("	     rcmlist_no	  	  = ?,	");
    	query.append("	     description	  = ?,	");
    	query.append("	     rcmlist_status	  = ?,	");
    	query.append("	     eqloc_id	  	  = ?,	");
    	query.append("	     rcm_categ	      = ?,	");
    	query.append("	     critylist_id	  = ?,	");
    	query.append("	     pmtaskmaplist_id = ?,	");
    	query.append("	     start_date   	  = ?,  ");
    	query.append("	     end_date   	  = ?,  ");
    	query.append("	     reg_date   	  = ?,  ");
    	query.append("	     remark	  		  = ?   ");
    	query.append("WHERE  comp_no      	  = ?	");
    	query.append("  AND  rcmlist_id       = ?	");
    	
    	Object[] objects = new Object[] {
    			rcmSysDetailDTO.getRcmListNo(),
    			rcmSysDetailDTO.getDescription(),
    			rcmSysDetailDTO.getRcmListStatus(),
    			rcmSysDetailDTO.getEqLocId(),
    			rcmSysDetailDTO.getRcmCateg(),
    			rcmSysDetailDTO.getCrityListId(),
    			rcmSysDetailDTO.getPmTaskMapListId(),
    			rcmSysDetailDTO.getStartDate(),
    			rcmSysDetailDTO.getEndDate(),
    			rcmSysDetailDTO.getRegDate(),
    			rcmSysDetailDTO.getRemark(),
    			user.getCompNo(),
    			rcmSysDetailDTO.getRcmListId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}