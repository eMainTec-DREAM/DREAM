package dream.work.let.categ.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.let.categ.dao.WorkLetCategEtcDetailDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;

/**
 * 안전작업유형 - 보완사항 Detail Page - Detail DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategEtcDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategEtcDetailDAOTarget"
 * @spring.txbn id="workLetCategEtcDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategEtcDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetCategEtcDetailDAO
{
	
    public WorkLetCategEtcDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT 																															");
        query.append("comp_no 																										 AS compNo			");
        query.append(", description    																								 AS description		");
        query.append(", woletctgetc_id 																								 AS woLetCtgEtcId	");
        query.append(", SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', 'ko')        										 		 AS isUseDesc		");
        query.append(", is_use        																						 		 AS isUseId			");
        query.append(", ord_no          																							 AS ordNo     		");
        query.append(", remark        																								 AS remark			");
        query.append(", (SELECT description FROM TAWOLETCTG  WHERE comp_no = a.comp_no AND woletctg_id = a.woletctg_id)              AS woLetCtgId      ");
        query.append("FROM TAWOLETCTGETC a																												");
        query.append("WHERE  1=1																														");
    	query.append("AND    woletctgetc_id  = ?																										");
    	query.append("AND    comp_no   		 = ?																										");
        
        Object[] objects = new Object[] {
        		workLetCategEtcListDTO.getWoLetCtgEtcId()
    			,user.getCompNo()
    	};
    
        WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO = 
        		(WorkLetCategEtcDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkLetCategEtcDetailDTO()));
        
        return workLetCategEtcDetailDTO;
        
    }
    

    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWOLETCTGETC (		");
    	query.append("comp_no							");
    	query.append(", woletctg_id						");
    	query.append(", woletctgetc_id					");
    	query.append(", description						");
    	query.append(", is_use							");
    	query.append(", ord_no							");
    	query.append(", remark							");
    	query.append(")									");
    	query.append("VALUES (							");
    	query.append("?									");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(")									");

    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,workLetCategCommonDTO.getWoLetCtgId()
    			,workLetCategEtcDetailDTO.getWoLetCtgEtcId()
    			,workLetCategEtcDetailDTO.getDescription()
    			,workLetCategEtcDetailDTO.getIsUseId()
    			,workLetCategEtcDetailDTO.getOrdNo()
    			,workLetCategEtcDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWOLETCTGETC SET 		");
    	query.append("description 			= ?		");
    	query.append(", is_use 				= ?		");
    	query.append(", ord_no 				= ?		");
    	query.append(", remark 				= ?		");
    	query.append("WHERE comp_no 		= ?		");
    	query.append("AND woletctgetc_id 	= ?		");

    	
    	Object[] objects = new Object[] {
    			workLetCategEtcDetailDTO.getDescription()
    			,workLetCategEtcDetailDTO.getIsUseId()
    			,workLetCategEtcDetailDTO.getOrdNo()
    			,workLetCategEtcDetailDTO.getRemark()
    			,user.getCompNo()
    			,workLetCategEtcDetailDTO.getWoLetCtgEtcId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}