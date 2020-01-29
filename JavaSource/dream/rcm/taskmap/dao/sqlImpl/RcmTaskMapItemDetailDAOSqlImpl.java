package dream.rcm.taskmap.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.taskmap.dao.RcmTaskMapItemDetailDAO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmTaskMapItemDetailDAOTarget"
 * @spring.txbn id="rcmTaskMapItemDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmTaskMapItemDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmTaskMapItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemListDTO
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public RcmTaskMapItemDetailDTO findDetail(RcmTaskMapItemListDTO rcmTaskMapItemListDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmTaskMapCommonDTO.getCompNo();
        
        query.append(" SELECT		");
        query.append("        pmtaskmap_no taskMapNo		");
        query.append("        ,description taskDesc		");
        query.append("        ,taskmap_rslt_ytype ytypeId		");
        query.append("        ,taskmap_rslt_ntype ntypeId		");
        query.append("        ,dbo.SFACODE_TO_DESC(x.taskmap_rslt_ytype,'TASKMAP_RSLT_TYPE','SYS','','"+user.getLangId()+"') ytypeDesc		");
        query.append("        ,dbo.SFACODE_TO_DESC(x.taskmap_rslt_ntype,'TASKMAP_RSLT_TYPE','SYS','','"+user.getLangId()+"') ntypeDesc		");
        query.append("        ,pmtaskmap_yid yid		");
        query.append("        ,pmtaskmap_nid nid		");
        query.append("        ,(SELECT z.pmtaskmap_no FROM TAPMTASKMAP z WHERE x.pmtaskmap_yid = z.pmtaskmap_id AND x.comp_no=z.comp_no ) yidDesc		");
        query.append("        ,(SELECT z.pmtaskmap_no FROM TAPMTASKMAP z WHERE x.pmtaskmap_nid = z.pmtaskmap_id AND x.comp_no=z.comp_no ) nidDesc      ");
        query.append("        ,rslt_ntask ntask		");
        query.append("        ,rslt_ytask ytask		");
        query.append("        ,remark remark		");
        query.append("        ,pmtaskmap_id pmTaskmapId		");
        query.append(" FROM TAPMTASKMAP x		");

        query.append("WHERE 1=1	");
        query.append("AND x.comp_no = '"+compNo+"'						");
        query.getAndQuery("x.pmtaskmap_id",rcmTaskMapItemListDTO.getPmTaskmapId());
    
        RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO1 = 
        		(RcmTaskMapItemDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmTaskMapItemDetailDTO()));
        
        return rcmTaskMapItemDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailDTO
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public int updateDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPMTASKMAP SET				");
    	query.append("	description				    = ?	,	");
    	query.append("	taskmap_rslt_ytype			= ?	,	");
    	query.append("	taskmap_rslt_ntype	     	= ?	,	");
    	query.append("	pmtaskmap_yid			    = ?	,	");
    	query.append("	pmtaskmap_nid			    = ?,		");
    	query.append("	rslt_ntask				    = ?,		");
    	query.append("	rslt_ytask				    = ?,		");
    	query.append("	remark			    	    = ?		");
    	query.append("WHERE pmtaskmap_id 		    = ?		");
    	query.append("  AND comp_no			    	= ? 	");
    	
    	Object[] objects = new Object[] {
    			rcmTaskMapItemDetailDTO.getTaskDesc()
    			,rcmTaskMapItemDetailDTO.getYtypeId()
    			,rcmTaskMapItemDetailDTO.getNtypeId()
    			,rcmTaskMapItemDetailDTO.getYid()
    			,rcmTaskMapItemDetailDTO.getNid()
    			,rcmTaskMapItemDetailDTO.getNtask()
    			,rcmTaskMapItemDetailDTO.getYtask()
    			,rcmTaskMapItemDetailDTO.getRemark()
    			,rcmTaskMapItemDetailDTO.getPmTaskmapId()
    			,rcmTaskMapCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailDTO
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public int insertDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPMTASKMAP (								");
    	query.append("	comp_no,		pmtaskmap_id,		    pmtaskmaplist_id,	");
    	query.append("	pmtaskmap_no ,	description,		    taskmap_rslt_ytype,		");
    	query.append("	taskmap_rslt_ntype,   pmtaskmap_yid,    pmtaskmap_nid,		    ");
    	query.append("	rslt_ntask,   rslt_ytask,               remark		    ");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,              ? ,					?,				");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,					?				");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmTaskMapCommonDTO.getCompNo()
    			,rcmTaskMapItemDetailDTO.getPmTaskmapId()
    			,rcmTaskMapCommonDTO.getPmTaskMapListId()
    			,rcmTaskMapItemDetailDTO.getTaskMapNo()
    			,rcmTaskMapItemDetailDTO.getTaskDesc()
    			,rcmTaskMapItemDetailDTO.getYtypeId()
    			,rcmTaskMapItemDetailDTO.getNtypeId()
    			,rcmTaskMapItemDetailDTO.getYid()
    			,rcmTaskMapItemDetailDTO.getNid()
    			,rcmTaskMapItemDetailDTO.getNtask()
    			,rcmTaskMapItemDetailDTO.getYtask()
    			,rcmTaskMapItemDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
	@Override
	public String taskNo(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT COUNT(*)+1 taskNo 		");
        query.append(" FROM TAPMTASKMAP WHERE pmtaskmaplist_id='"+rcmTaskMapCommonDTO.getPmTaskMapListId()+"'		");

        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
}