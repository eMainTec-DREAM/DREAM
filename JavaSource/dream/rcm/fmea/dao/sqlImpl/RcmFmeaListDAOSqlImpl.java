package dream.rcm.fmea.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.fmea.dao.RcmFmeaListDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;


/**
 * dao
 * @author  kim21017
 * @version $Id: RcmFmeaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFmeaListDAOTarget"
 * @spring.txbn id="rcmFmeaListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFmeaListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFmeaListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFmeaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @return List
     */
    public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 								");
        query.append("       '' AS seqNo,					");
        query.append("       x.rcmlist_no rcmlistNo         ");
        query.append("       ,x.description rcmListDesc		");
        query.append("       ,d.item_no equipNo,            ");
        query.append("       d.description equipDesc,   	");
        query.append("       y.rcmfunc_no rcmfuncNo, 		");
        query.append("       y.description funcDesc,		");
        query.append("       z.rcmffail_no rcmffailNo,    	");
        query.append("       z.description failDesc,    	");
        query.append("       e.description asmbDesc,    	");
        query.append("       f.mofail_desc mofailDesc,    	");
        query.append("       f.cafail_desc cafailDesc,    	");
        query.append("       f.effail_desc effailDesc,    	");
        query.append("       x.rcmlist_id rcmlistId,    	");
        query.append("       y.rcmfunc_id rcmfuncId,    	");
        query.append("       z.rcmffail_id rcmffailId,    	");
        query.append("       b.rcmeq_id rcmeqId,        	");
        query.append("       c.rcmeqasmb_id rcmeqasmbId,	");
        query.append("       x.critylist_id critylistId,	");
        query.append("       f.rcmfmea_id rcmfmeaId     	");
        query.append("       ,f.remark remark     			");
        query.append("FROM   TARCMLIST x                    ");
        query.append("       LEFT OUTER JOIN TARCMFUNC y ON x.rcmlist_id = y.rcmlist_id                        	");
        query.append("       LEFT OUTER JOIN TARCMFFAIL z ON y.rcmfunc_id = z.rcmfunc_id                		");
        query.append("       INNER JOIN TARCMFFEQ b INNER JOIN TARCMEQ k INNER JOIN TAEQUIPMENT d ON k.equip_id = d.equip_id ON b.rcmeq_id = k.rcmeq_id ON z.rcmffail_id = b.rcmffail_id  	");
        query.append("       LEFT OUTER JOIN TARCMEQASMB c  INNER JOIN TAEQASMB e ON e.eqasmb_id = c.eqasmb_id ON  b.rcmeqasmb_id = c.rcmeqasmb_id                     		");
        query.append("       LEFT OUTER JOIN TARCMFMEA f ON z.rcmffail_id = f.rcmffail_id AND b.rcmffeq_id = f.rcmffeq_id and ISNULL(b.rcmeqasmb_id,0) = ISNULL(f.rcmeqasmb_id,0)          		");
        query.append("WHERE  1=1                                                                   				");
        query.append(this.getWhere(rcmFmeaCommonDTO));
        query.getOrderByQuery("x.rcmlist_id","x.rcmlist_no desc", rcmFmeaCommonDTO.getOrderBy(), rcmFmeaCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmFmeaCommonDTO.getIsLoadMaxCount(), rcmFmeaCommonDTO.getFirstRow()));

    }
    
    /**
     * Filter Á¶°Ç
     * @author  kim21017
     * @version $Id: RcmFmeaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(RcmFmeaCommonDTO rcmFmeaCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(rcmFmeaCommonDTO.getRcmlistId()))
        {
        	query.getAndNumKeyQuery("x.rcmlist_id", rcmFmeaCommonDTO.getRcmlistId());
        	query.getAndNumKeyQuery("y.rcmfunc_id", rcmFmeaCommonDTO.getRcmfuncId());
        	query.getAndNumKeyQuery("z.rcmffail_id", rcmFmeaCommonDTO.getRcmffailId());
        	query.getAndNumKeyQuery("b.rcmeq_id", rcmFmeaCommonDTO.getRcmeqId());
        	query.getAndNumKeyQuery("c.rcmeqasmb_id", rcmFmeaCommonDTO.getRcmeqasmbId());
        	
            query.getAndNumKeyQuery("f.rcmfmea_id", rcmFmeaCommonDTO.getRcmfmeaId());
            return query.toString();
        }

        query.getCodeLikeQuery("x.rcmlist_id", "x.description", 
        		rcmFmeaCommonDTO.getFilterRcmlistId(), rcmFmeaCommonDTO.getFilterRcmlistDesc());
        
        
        return query.toString();
    }

	@Override
	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 								");
        query.append("  COUNT(1)							");
        query.append("FROM   TARCMLIST x                    ");
        query.append("       LEFT OUTER JOIN TARCMFUNC y ON x.rcmlist_id = y.rcmlist_id                        	");
        query.append("       LEFT OUTER JOIN TARCMFFAIL z ON y.rcmfunc_id = z.rcmfunc_id                		");
        query.append("       INNER JOIN TARCMFFEQ b INNER JOIN TARCMEQ k INNER JOIN TAEQUIPMENT d ON k.equip_id = d.equip_id ON b.rcmeq_id = k.rcmeq_id ON z.rcmffail_id = b.rcmffail_id  	");
        query.append("       LEFT OUTER JOIN TARCMEQASMB c  INNER JOIN TAEQASMB e ON e.eqasmb_id = c.eqasmb_id ON  b.rcmeqasmb_id = c.rcmeqasmb_id                     		");
        query.append("       LEFT OUTER JOIN TARCMFMEA f ON z.rcmffail_id = f.rcmffail_id AND b.rcmffeq_id = f.rcmffeq_id and ISNULL(b.rcmeqasmb_id,0) = ISNULL(f.rcmeqasmb_id,0)          		");
        query.append("WHERE  1=1                                                                   				");
        query.append(this.getWhere(rcmFmeaCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
   
	}
  
}