package dream.mgr.ptwh.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.ptwh.dao.MgrPtWhBinDetailDAO;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
/**
 * 부품창고 보관위치 - Detail DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrPtWhBinDetailDAOTarget"
 * @spring.txbn id="mgrPtWhBinDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPtWhBinDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrPtWhBinDetailDAO
{
	
    public MgrPtWhBinDetailDTO findPtWhEmpDetail(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	 query.append("SELECT        																										");
         query.append("       ''                                                                                           	AS seqNo        ");
         query.append("       ,''                                                                                         	AS ISDELCHECK	");
         query.append("       ,x.BIN_NO																						AS binNo 		");
         query.append("       ,x.LOC																						AS loc 			");
         query.append("       ,x.COL																						AS col 			");
         query.append("       ,x.RO																							AS ro	 		");
         query.append("       ,x.REMARK                                                                                     AS remark 		");
         query.append("       ,x.PTBINLIST_ID                                                                               AS ptBinListId	");
         query.append("       ,x.WCODE_ID                                                                                   AS wCodeId		");
         query.append("       ,x.is_use                                                                                    	AS isUse		");
         query.append("FROM TAPTBINLIST x        																							");
         query.append("WHERE 1=1																											");
         query.append(" AND x.comp_no = ?																									");
         query.append(" AND x.WCODE_ID = ?																									");
         query.append(" AND x.PTBINLIST_ID = ?																								");

        Object[] objects = new Object[] {
                user.getCompNo()
                , mgrPtWhBinListDTO.getWcodeId()
                , mgrPtWhBinListDTO.getPtBinListId()
    	};
    
        MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO = 
        		(MgrPtWhBinDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrPtWhBinDetailDTO()));
        
        return mgrPtWhBinDetailDTO;
        
    }
    

    public int insertPtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

    	query.append("INSERT INTO TAPTBINLIST(              ");
    	query.append("		comp_no                         ");
    	query.append("		,PTBINLIST_ID                   ");
    	query.append("		,WCODE_ID  						");
    	query.append("		,BIN_NO           				");
    	query.append("		,LOC           					");
    	query.append("		,COL           					");
    	query.append("		,RO           					");
    	query.append("		,REMARK                         ");
    	query.append("		,is_use                         ");
    	query.append(" ) VALUES(                         	");
    	query.append("		?                               ");
    	query.append("		,?                              ");
    	query.append("		,?  							");
    	query.append("		,?                              ");
    	query.append("		,?                              ");
    	query.append("		,?                              ");
    	query.append("		,?                              ");
    	query.append("		,?                              ");
    	query.append("		,?                              ");
    	query.append(" )                                 	");

    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 ,mgrPtWhBinDetailDTO.getPtBinListId()
    			 ,mgrPtWhBinDetailDTO.getWcodeId()
    			 ,mgrPtWhBinDetailDTO.getBinNo()
    			 ,mgrPtWhBinDetailDTO.getLoc()
    			 ,mgrPtWhBinDetailDTO.getCol()
    			 ,mgrPtWhBinDetailDTO.getRo()
    			 ,mgrPtWhBinDetailDTO.getRemark()
    			 ,mgrPtWhBinDetailDTO.getIsUse()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
        return rtnValue;
    }
    
    
    
    public int updatePtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;

       	query.append("UPDATE TAPTBINLIST SET                		");
    	query.append("    bin_no               	= ?   				");
    	query.append("    ,loc               	= ?   				");
    	query.append("    ,col               	= ?   				");
    	query.append("    ,ro               	= ?   				");
    	query.append("    ,remark               = ?   				");
    	query.append("    ,is_use               = ?   				");
    	query.append("WHERE comp_no 			= ?					");
        query.append(" AND WCODE_ID	 			= ?					");
        query.append(" AND PTBINLIST_ID 		= ?					");

    	Object[] objects = new Object[] {
   			 	mgrPtWhBinDetailDTO.getBinNo()
   			 	,mgrPtWhBinDetailDTO.getLoc()
   			 	,mgrPtWhBinDetailDTO.getCol()
   			 	,mgrPtWhBinDetailDTO.getRo()
    			,mgrPtWhBinDetailDTO.getRemark()
    			,mgrPtWhBinDetailDTO.getIsUse()
    			,user.getCompNo()
    			,mgrPtWhBinDetailDTO.getWcodeId()
    			,mgrPtWhBinDetailDTO.getPtBinListId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
        return rtnValue;
    }


	@Override
	public String validEmpNo(MgrPtWhBinListDTO mgrPtWhBinListDTO, MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT COUNT(*)                 		");
        query.append("FROM   TAPTBINLIST              		");
        query.append("WHERE 1=1								");
        query.append(" AND comp_no	 		= ?				");
        query.append(" AND WCODE_ID 		= ?				");
        query.append(" AND emp_id 			= ?				");

        // 보관위치 ID
        if(!"".equals(mgrPtWhBinListDTO.getPtBinListId())){
            query.append(" AND ptBinList_id != '" + mgrPtWhBinListDTO.getPtBinListId() + "'");
        }
        
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,mgrPtWhBinDetailDTO.getWcodeId()
    	};
    	   
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
	}
}