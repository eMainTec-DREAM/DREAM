package dream.mgr.ptwh.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.ptwh.dao.MgrPtWhBinListDAO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;

/**
 * 부품창고 보관위치 - List DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrPtWhBinListDAOTarget"
 * @spring.txbn id="mgrPtWhBinListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPtWhBinListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrPtWhBinListDAO
{
	public List findPtWhEmpList(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT        																									");
        query.append("        ''																						AS seqNo		");
        query.append("       ,''																						AS ISDELCHECK	");
        query.append("       ,x.BIN_NO																					AS binNo 		");
        query.append("       ,x.LOC																						AS loc 			");
        query.append("       ,x.COL																						AS col 			");
        query.append("       ,x.RO																						AS ro			");
        query.append("       ,x.REMARK																					AS remark 		");
        query.append("       ,x.PTBINLIST_ID                  													 		AS ptBinListId	");
        query.append("       ,x.WCODE_ID                  													  			AS wCodeId		");
        query.append("       ,x.is_use                  													  			AS isUse		");
        query.append("FROM TAPTBINLIST x																								");
        query.append("WHERE 1=1																											");
        query.append(this.getWhere(mgrPtWhBinListDTO, user));
        query.getOrderByQuery("x.PTBINLIST_ID", mgrPtWhBinListDTO.getOrderBy(), mgrPtWhBinListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(mgrPtWhBinListDTO.getIsLoadMaxCount(), mgrPtWhBinListDTO.getFirstRow()));
    }

	private String getWhere(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        // 창고 보관위치 ID
        if(!"".equals(mgrPtWhBinListDTO.getPtBinListId())){
            query.getAndQuery("x.PTBINLIST_ID", mgrPtWhBinListDTO.getPtBinListId());
            return query.toString();
        }
        	
        // 창고 ID
        query.getAndQuery("x.wcode_id", mgrPtWhBinListDTO.getWcodeId());
        
        return query.toString();
    } 

    public int deletePtWhEmpList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAPTBINLIST			");
        query.append("WHERE  comp_no 			= ?		");
        query.append(" AND PTBINLIST_ID 		= ?		");

        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String findTotalCount(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("       COUNT(1)							");
        query.append("FROM TAPTBINLIST x						");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(mgrPtWhBinListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }

    @Override
    public int deleteQrList(User user, String fileName) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAREPORTPARAM    ");
        query.append("WHERE  comp_no   = ?    ");
        query.append("AND    user_id   = ?    ");
        query.append("AND    file_name = ?    ");

        Object[] objects = new Object[]{
                user.getCompNo()
                ,user.getUserId()
                ,fileName
        };

        int result = this.getJdbcTemplate().update(query.toString(),objects);

        return result;
    }

    @Override
    public int insertQrList(String id, String fileName, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query = new QueryBuffer();
        
        query.append("INSERT INTO TAREPORTPARAM (   ");
        query.append("        comp_no               ");
        query.append("        ,user_id              ");
        query.append("        ,skey_id              ");
        query.append("        ,file_name            ");
        query.append(")                             ");
        query.append("VALUES (                      ");
        query.append("        ?                     ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append(")                             ");
        
        Object[] objects = new Object[]{
                user.getCompNo(),
                user.getUserId(),
                id,
                fileName
        };
        
        int result = this.getJdbcTemplate().update(query.toString(),objects);

        return result;
    }
}