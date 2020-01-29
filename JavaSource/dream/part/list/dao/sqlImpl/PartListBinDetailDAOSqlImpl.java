package dream.part.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.PartListBinDetailDAO;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;
/**
 * 부품창고 보관위치 - Detail DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partListBinDetailDAOTarget"
 * @spring.txbn id="partListBinDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartListBinDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartListBinDetailDAO
{
	
    public PartListBinDetailDTO findPtWhBinDetail(PartListBinListDTO partListBinListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT        																						");
        query.append("       ''                                                                         AS seqNo        	");
        query.append("       ,''                                                                        AS ISDELCHECK		");
        query.append("       ,(SELECT a.wname        																		");
        query.append("         FROM TAWAREHOUSE a        																	");
        query.append("         WHERE x.comp_no = a.comp_no        															");
        query.append("          and x.wcode_id = a.wcode_id)                                			AS    wname     	");
        query.append("       ,(SELECT b.bin_no        																		");
        query.append("         FROM TAPTBINLIST b        																	");
        query.append("         WHERE x.comp_no = b.comp_no        															");
        query.append("          and x.ptbinlist_id = b.ptbinlist_id)              						AS binNo        	");
        query.append("       ,x.bin_no																	AS binNoTxt 		");
        query.append("       ,x.ptwhbinno_id                                                            AS ptwhBinNoId		");
        query.append("       ,x.ptbinlist_id                                                            AS ptBinListId		");
        query.append("       ,x.wcode_id                                                                AS wCodeId			");
        query.append("       ,x.part_id                                                                 AS partId			");
        query.append("  	 ,x.remark                                  								AS remark       	");
        query.append("       ,x.part_grade                                         						AS partGrade    	");
        query.append("       ,(SELECT dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','',?) )		AS partGradeDesc	");
        query.append("FROM TAPTWHBINNO x        																			");
        query.append("WHERE 1 = 1																							");
        query.append(" AND x.comp_no = ?																					");
        query.append(" AND x.ptwhbinno_id = ?																				");
        query.append(" AND x.part_id = ?																					");

          Object[] objects = new Object[] {
        		user.getLangId()
                , user.getCompNo()
                , partListBinListDTO.getPtwhBinNoId()
                , partListBinListDTO.getPartId()
      	};
      
          PartListBinDetailDTO partListBinDetailDTO = 
          		(PartListBinDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PartListBinDetailDTO()));
        
        return partListBinDetailDTO;
        
    }
    

    public int insertPtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAPTWHBINNO(               					");
    	query.append("	 comp_no		,ptwhbinno_id	,ptbinlist_id           ");
    	query.append("	,wcode_id  		,part_id  		,bin_no           		");
    	query.append("	,part_grade		,remark           						");
    	query.append(")                          								");
    	query.append("VALUES(                         							");
    	query.append("	 ?              ,?              ,?                      ");
    	query.append("	,?              ,?              ,?                      ");
    	query.append("	,?				,?                                   	");
    	query.append(")                                 						");

    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 ,partListBinDetailDTO.getPtwhBinNoId()
    			 ,partListBinDetailDTO.getPtBinListId()
    			 ,partListBinDetailDTO.getWcodeId()
    			 ,partListBinDetailDTO.getPartId()
    			 ,partListBinDetailDTO.getBinNoTxt()
    			 ,partListBinDetailDTO.getPartGrade()
    			 ,partListBinDetailDTO.getRemark()
    	};
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
        return rtnValue;
    }
    
    
    
    public int updatePtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;

    	query.append("UPDATE TAPTWHBINNO SET                		");
    	query.append("    bin_no               	= ?   				");
    	query.append("    ,ptwhbinno_id         = ?   				");
    	query.append("	  ,ptbinlist_id         = ?         		");
    	query.append("    ,remark               = ?   				");
    	query.append("    ,wcode_id	 			= ?					");
    	query.append("    ,part_grade 			= ?					");
    	query.append("WHERE comp_no 			= ?					");
        query.append(" AND ptwhbinno_id 		= ?					");
        query.append(" AND part_id 				= ?					");

    	Object[] objects = new Object[] {
   			 	partListBinDetailDTO.getBinNoTxt()
   			    ,partListBinDetailDTO.getPtwhBinNoId()
   			    ,partListBinDetailDTO.getPtBinListId()
    			,partListBinDetailDTO.getRemark()
    			,partListBinDetailDTO.getWcodeId()
    			,partListBinDetailDTO.getPartGrade()
    			,user.getCompNo()
    			,partListBinDetailDTO.getPtwhBinNoId()
    			,partListBinDetailDTO.getPartId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
        return rtnValue;
    }


	@Override
	public String validPtBin(PartListBinListDTO partListBinListDTO, PartListBinDetailDTO partListBinDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		  query.append("SELECT COUNT(*)                 		");
		  query.append("FROM TAPTWHBINNO              			");
	      query.append("WHERE 1=1								");   
	      query.append(" 	AND comp_no	 		= ?				");
	      query.append(" 	AND wcode_id 		= ?				");
	      query.append(" 	AND part_id 		= ?				");
	      query.append(" 	AND ptbinlist_id 	= ?				");
	      query.append(" 	AND part_grade	 	= ?				");

	      // 보관위치 ID
	      if(!"".equals(partListBinListDTO.getPtwhBinNoId())){
	          query.append(" AND ptwhbinno_id != '" + partListBinListDTO.getPtwhBinNoId() + "'");
	      }
	      
	      Object[] objects = new Object[] {
    			user.getCompNo()
    			,partListBinDetailDTO.getWcodeId()
    			,partListBinDetailDTO.getPartId()
    			,partListBinDetailDTO.getPtBinListId()
    			,partListBinDetailDTO.getPartGrade()
	    };
    	   
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
	}

}