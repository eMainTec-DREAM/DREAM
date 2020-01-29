package dream.mgr.contract.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.contract.dao.LovMgrContractListDAO;
import dream.mgr.contract.dto.LovMgrContractListDTO;

/**
 * 단가계약 LOV - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="lovMgrContractListDAOTarget"
 * @spring.txbn id="lovMgrContractListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovMgrContractListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovMgrContractListDAO
{
	public List findList(LovMgrContractListDTO lovMgrContractListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                            														");
        query.append("      x.contract_id 																		contractId		");
		query.append("    , x.contract_no 																		contractNo		");
		query.append("    , x.description 																		contractDesc	");
		query.append("	  , x.vendor_id																			vendorId		");
		query.append("    , (SELECT a.vendor_no FROM TAVENDOR a 																");
		query.append("        WHERE a.comp_no = x.comp_no 																		");
		query.append("             AND a.vendor_id = x.vendor_id) 												vendorNo		");
		query.append("    , (SELECT a.description FROM TAVENDOR a 																");
		query.append("        WHERE a.comp_no = x.comp_no 																		");
		query.append("             AND a.vendor_id = x.vendor_id) 												vendorDesc		");
		query.append("    , x.contract_date 																	contractDate	");
		query.append("    , x.start_date + '~' + x.end_date 													contractPeriod	");
		query.append("    , x.start_date 																		contractStartDate		");
		query.append("    , x.end_date 																			contractEndDate			");
		query.append("    , x.tot_amt 																			contractAmount	");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getCompNo()+"') ) 	isUse			");
		query.append("    , x.remark 																			remark			");

        query.append("FROM TACONTRACT x                                															");
        query.append("WHERE  1 = 1                                      														");
        query.append(this.getWhere(lovMgrContractListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.contract_no", lovMgrContractListDTO.getOrderBy(), lovMgrContractListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovMgrContractListDTO.getIsLoadMaxCount(), lovMgrContractListDTO.getFirstRow()));
    } 

	private String getWhere(LovMgrContractListDTO lovMgrContractListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());

        //계약#
        query.getLikeQuery("x.contract_no", lovMgrContractListDTO.getFilterContractNo());
        
        //계약명
        query.getLikeQuery("x.description", lovMgrContractListDTO.getFilterContractDesc());
        
        //업체명
        query.getCodeLikeQuery("x.vendor_id", "(SELECT a.description FROM  TAVENDOR a WHERE a.comp_no = x.comp_no AND a.vendor_id = x.vendor_id )", 
        		lovMgrContractListDTO.getFilterVendorId(), lovMgrContractListDTO.getFilterVendorDesc());
        
        //서비스명
//        query.getCodeLikeQuery("x.vendor_id", "(SELECT a.description FROM  TAVENDOR a WHERE a.comp_no = x.comp_no AND a.vendor_id = x.vendor_id )", 
//        		lovMgrContractListDTO.getFilterVendorId(), lovMgrContractListDTO.getFilterVendorDesc());
        
        //계약일자
        query.getAndDateQuery("x.contract_date", lovMgrContractListDTO.getFilterContractStartDate(), lovMgrContractListDTO.getFilterContractEndDate());
        
        //계약기간시작일자
        query.getAndDateQuery("x.start_date", lovMgrContractListDTO.getFilterContractFromStartDate(), lovMgrContractListDTO.getFilterContractFromEndDate());
        
        //계약기간종료일자
        query.getAndDateQuery("x.end_date", lovMgrContractListDTO.getFilterContractToStartDate(), lovMgrContractListDTO.getFilterContractToEndDate());
        
        //사용여부
        query.getSysCdQuery("x.is_use", lovMgrContractListDTO.getFilterIsUse(), lovMgrContractListDTO.getFilterIsUse(), "IS_USE", user.getCompNo(), user.getLangId());
        
        query.getAndQuery("x.is_use", conditionMap);

    	return query.toString();
    }

    public String findTotalCount(LovMgrContractListDTO lovMgrContractListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TACONTRACT x             ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(lovMgrContractListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}