package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.PartListBinListDAO;
import dream.part.list.dto.PartListBinListDTO;

/**
 * 부품창고 보관위치 - List DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partListBinListDAOTarget"
 * @spring.txbn id="partListBinListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartListBinListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartListBinListDAO
{
	public List findPtWhBinList(PartListBinListDTO partListBinListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("  SELECT          																		");
        query.append("         ''                                                           AS seqNo           	");
        query.append("       ,''                                                            AS ISDELCHECK      	");
        query.append("       ,(SELECT a.wname        															");
        query.append("         FROM TAWAREHOUSE a        														");
        query.append("         WHERE x.comp_no = a.comp_no        												");
        query.append("          AND x.wcode_id = a.wcode_id)                                AS wname        	");
        query.append("       ,(SELECT b.bin_no        															");
        query.append("         FROM TAPTBINLIST b        														");
        query.append("         WHERE x.comp_no = b.comp_no        												");
        query.append("          AND x.ptbinlist_id = b.ptbinlist_id)                       	AS binNo           	");
        query.append("       , x.bin_no                                   					AS binNoTxt        	");
        query.append("  	 ,x.remark                                  					AS remark          	");
        query.append("       ,x.PTWHBINNO_ID                                                AS ptwhBinNoId     	");
        query.append("       ,x.WCODE_ID                                                    AS wCodeId         	");
        query.append("       ,x.PART_ID                                                     AS partId         	");
        query.append("       ,x.part_grade                                         			AS partGrade    	");
        query.append("       ,(SELECT SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') FROM DUAL)	AS partGradeDesc	");
        query.append("  FROM TAPTWHBINNO x        																");
        query.append("  WHERE 1 = 1        																		");
        query.append(this.getWhere(partListBinListDTO, user));
        query.getOrderByQuery("x.ptwhbinno_id", partListBinListDTO.getOrderBy(), partListBinListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(partListBinListDTO.getIsLoadMaxCount(), partListBinListDTO.getFirstRow()));
    }

	private String getWhere(PartListBinListDTO partListBinListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        // 창고 보관위치 ID
        if(!"".equals(partListBinListDTO.getPtwhBinNoId())){
            query.getAndQuery("x.ptwhbinno_id", partListBinListDTO.getPtwhBinNoId());
            return query.toString();
        }
        	
        // 창고 ID
        query.getAndQuery("x.wcode_id", partListBinListDTO.getWcodeId());
        
        // 부품 ID
        query.getAndQuery("x.part_id", partListBinListDTO.getPartId());

        // 재고등급
        query.getAndQuery("x.part_grade", partListBinListDTO.getPartGrade());
        
        return query.toString();
    } 

    public int deletePtWhBinList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM taptwhbinno			");
        query.append("WHERE  comp_no 			= ?		");
        query.append(" AND ptwhbinno_id 		= ?		");

        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String findTotalCount(PartListBinListDTO partListBinListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("       COUNT(1)							");
        query.append("FROM TAPTWHBINNO x						");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(partListBinListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}