package dream.mgr.ptwh.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.ptwh.dao.MgrPtWhEmpDetailDAO;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;

/**
 * 부품창고 담당자 - Detail DAO implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrPtWhEmpDetailDAOTarget"
 * @spring.txbn id="mgrPtWhEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPtWhEmpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrPtWhEmpDetailDAO
{
	
    public MgrPtWhEmpDetailDTO findPtWhEmpDetail(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        																										");
        query.append("       ''                                                                                           	AS seqNo        ");
        query.append("       ,''                                                                                         	AS ISDELCHECK	");
        query.append("       ,(SELECT description																							");
        query.append("         FROM   TADEPT																								");
        query.append("         WHERE  comp_no = x.comp_no																					");
        query.append("          AND dept_id = (SELECT dept_id FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.emp_id))	AS deptDesc		");
        query.append("       ,(SELECT emp_no                                																");
        query.append("          FROM TAEMP                                																	");
        query.append("         WHERE comp_no = x.comp_no                																	");
        query.append("           AND emp_id = x.emp_id)                                                                   	AS empNo 		");
        query.append("       ,(SELECT emp_name                            																	");
        query.append("          FROM TAEMP                                																	");
        query.append("         WHERE comp_no = x.comp_no                																	");
        query.append("           AND emp_id = x.emp_id)                                                                    	AS empDesc 		");
        query.append("       ,x.REMARK                                                                                     	AS remark 		");
        query.append("       ,x.WHUSER_ID                                                                                  	AS whUserId		");
        query.append("       ,x.EMP_ID                                                                                  	AS empId		");
        query.append("       ,x.WCODE_ID                                                                                    AS wCodeId		");
        query.append("FROM TAWHUSER x        																								");
        query.append("WHERE 1=1																												");
        query.append(" AND x.comp_no = ?																									");
        query.append(" AND x.WCODE_ID = ?																									");
        query.append(" AND x.WHUSER_ID = ?																									");

        Object[] objects = new Object[] {
                user.getCompNo()
                , mgrPtWhEmpListDTO.getWcodeId()
                , mgrPtWhEmpListDTO.getWhUserId()
    	};
    
        MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO = 
        		(MgrPtWhEmpDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrPtWhEmpDetailDTO()));
        
        return mgrPtWhEmpDetailDTO;
        
    }
    

    public int insertPtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAWHUSER(               						");
    	query.append("comp_no                           						");
    	query.append(",WHUSER_ID                        						");
    	query.append(",WCODE_ID  												");
    	query.append(",WCODE           											");
    	query.append(",EMP_ID                           						");
    	query.append(",EMP_NO                           						");
    	query.append(",REMARK                           						");
    	query.append(") VALUES(                         						");
    	query.append("?                                 						");
    	query.append(",?                                						");
    	query.append(",?  														");
    	query.append(",(SELECT y.WCODE FROM TAWAREHOUSE y WHERE y.WCODE_ID=?)	");
    	query.append(",?                                						");
    	query.append(",?                                						");
    	query.append(",?                                   						");
    	query.append(")                                 						");
    	query.append("															");
    	query.append("															");

    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 ,mgrPtWhEmpDetailDTO.getWhUserId()
    			 ,mgrPtWhEmpDetailDTO.getWcodeId()
    			 ,mgrPtWhEmpDetailDTO.getWcodeId()
    			 ,mgrPtWhEmpDetailDTO.getEmpId()
    			 ,mgrPtWhEmpDetailDTO.getEmpNo()
    			 ,mgrPtWhEmpDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updatePtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWHUSER SET                		");
    	query.append("    emp_id          			= ?    		");
    	query.append("    ,emp_no               	= ?    		");
    	query.append("    ,remark               	= ?   		");
    	query.append("WHERE comp_no 		= ?					");
        query.append(" AND WCODE_ID	 		= ?					");
        query.append(" AND WHUSER_ID 		= ?					");

    	Object[] objects = new Object[] {
    			mgrPtWhEmpDetailDTO.getEmpId()
    			,mgrPtWhEmpDetailDTO.getEmpNo()
    			,mgrPtWhEmpDetailDTO.getRemark()
    			,user.getCompNo()
    			,mgrPtWhEmpDetailDTO.getWcodeId()
    			,mgrPtWhEmpDetailDTO.getWhUserId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }


	@Override
	public String validEmpNo(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                 		");
        query.append("FROM TAWHUSER              			");
        query.append("WHERE 1=1								");
                
        query.append(" AND comp_no	 		= ?				");
        query.append(" AND wcode_id 		= ?				");
        query.append(" AND emp_id 			= ?				");

        // 담당자 ID
        if(!"".equals(mgrPtWhEmpListDTO.getWhUserId())){
            query.append(" AND whuser_id != '" + mgrPtWhEmpListDTO.getWhUserId() + "'");
        }
        
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,mgrPtWhEmpDetailDTO.getWcodeId()
    			,mgrPtWhEmpDetailDTO.getEmpId()
    	};
    	    	
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
	}
}