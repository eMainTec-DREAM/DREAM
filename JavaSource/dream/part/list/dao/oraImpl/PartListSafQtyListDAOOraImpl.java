package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.PartListSafQtyListDAO;
import dream.part.list.dto.PartListSafQtyListDTO;

/**
 * 부품창고 보관위치 - List DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partListSafQtyListDAOTarget"
 * @spring.txbn id="partListSafQtyListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartListSafQtyListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartListSafQtyListDAO
{
	public List findPtWhEmpList(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                                                                                    ");
        query.append("       ''                                                                  AS seqNo       ");
        query.append("       ,''                                                                 AS ISDELCHECK  ");
        query.append("       ,(SELECT a.wname        															");
        query.append("       FROM tawarehouse a        															");
        query.append("       WHERE x.comp_no=A.COMP_NO AND X.WCODE_ID=A.WCODE_ID)                AS wname    	");
        query.append("       ,X.SAFTY_QTY                                                        AS minSaftyQty ");
        query.append("       ,X.MAX_SAFTY_QTY                                                    AS maxSaftyQty ");
        query.append("       ,x.WCODE_ID                                                         AS wCodeId		");
        query.append("       ,x.PART_ID                                                          AS partId		");
        query.append("FROM taptsaftystock x                                                                     ");
        query.append("WHERE 1=1        																			");
        query.append(this.getWhere(partListSafQtyListDTO, user));
        query.getOrderByQuery("x.part_id", partListSafQtyListDTO.getOrderBy(), partListSafQtyListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partListSafQtyListDTO.getIsLoadMaxCount(), partListSafQtyListDTO.getFirstRow()));
    }

	private String getWhere(PartListSafQtyListDTO partListSafQtyListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());

        query.getAndQuery("x.part_id", partListSafQtyListDTO.getPartId());
        
        return query.toString();
    } 

    public int deletePtWhEmpList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM taptsaftystock			");
        query.append("WHERE  comp_no 	= ?		");
        query.append(" AND PART_ID 		= ?		");

        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String findTotalCount(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("       COUNT(1)							");
        query.append("FROM taptsaftystock x						");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(partListSafQtyListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}