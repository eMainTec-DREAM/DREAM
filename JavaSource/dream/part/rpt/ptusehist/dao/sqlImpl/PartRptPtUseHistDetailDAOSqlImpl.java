package dream.part.rpt.ptusehist.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.part.rpt.ptusehist.dao.PartRptPtUseHistDetailDAO;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistDetailDTO;

/**
 * ��ǰ���м� - Detail DAO implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="partRptPtUseHistDetailDAOTarget"
 * @spring.txbn id="partRptPtUseHistDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartRptPtUseHistDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartRptPtUseHistDetailDAO
{

	public List findPartDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;								");

        query.append("SELECT																		");
        query.append("		''														AS	seqNo		");
        query.append("		,x.MONTH												AS	month		");
        query.append(" 		,COUNT(x.useQty)										AS 	useCnt		");
        query.append("      ,SUM(x.useQty)             								AS	useQty		");
        query.append("      ,ISNULL(SUM(x.totPrice),0)                              AS	totPrice	");
        query.append("      ,x.WKORID                              					AS	WKORID		");
        query.append("FROM (    																	");
        query.append("	SELECT																		");
        query.append("  	SUBSTRING(a.start_date,1,4) + '-' + SUBSTRING(a.start_date,5,2)	MONTH	");
        query.append("      ,b.use_qty                                          	AS	useQty 		");
        query.append("      ,ISNULL(b.tot_price,0)                             		AS	totPrice	");
        query.append("      ,a.wkor_id												AS	WKORID		");
        query.append("	FROM TAWORKORDER a INNER JOIN TAWOPARTS b                                   ");
        query.append("	ON a.comp_no = b.comp_no                                                    ");
        query.append("	 AND a.wkor_id = b.wkor_id                                                  ");
        query.append("	WHERE 1=1                                                                   ");
        query.append("	 AND a.wo_status = 'C'    													");
        query.append(this.getWhere(partRptPtUseHistDetailDTO, user));
        query.append(") x       																	");
        query.append("GROUP BY x.MONTH, x.WKORID                                                   	");

        query.getOrderByQuery("X.WKORID","x.MONTH", partRptPtUseHistDetailDTO.getOrderBy(), partRptPtUseHistDetailDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(partRptPtUseHistDetailDTO.getIsLoadMaxCount(), partRptPtUseHistDetailDTO.getFirstRow()));
    }

	@Override
	public List findEqDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;								");

		query.append("SELECT																		");
		query.append("		''														AS	seqNo		");
		query.append("		,(	SELECT  cc.full_desc												");
		query.append("        	FROM TAEQUIPMENT bb INNER JOIN TAEQLOC cc    						");
		query.append("        	ON bb.comp_no = cc.comp_no              							");
		query.append("        	 AND bb.eqloc_id = cc.eqloc_id										");
		query.append("        	WHERE bb.comp_no = '"+user.getCompNo()+"'							");
		query.append("        	 AND bb.equip_id = x.equipId  )         			AS	eqLocDesc	");
		query.append("      ,x.equipId                                              AS  equipId     ");
		query.append("		,(	SELECT item_no 														");
		query.append("			FROM TAEQUIPMENT 													");
		query.append("			WHERE equip_id = x.equipId )         				AS	equipNo		");
		query.append("		,(	SELECT description 													");
		query.append("			FROM TAEQUIPMENT 													");
		query.append("			WHERE comp_no = '"+user.getCompNo()+"' 								");
		query.append("			 AND equip_id = x.equipId   ) 						AS	equipDesc	");
		query.append("		,COUNT(x.useQty)                                      	AS	useCnt		");
		query.append("      ,SUM(x.useQty)                                       	AS	useQty		");
		query.append("      ,ISNULL(SUM(x.totPrice),0)                              AS	totPrice	");
		query.append("FROM (    																	");
		query.append("SELECT                                               							");
		query.append("      (	SELECT bb.EQUIP_ID            										");
		query.append("       	FROM TAWOEQUIP aa INNER JOIN TAEQUIPMENT bb    						");
		query.append("         	ON aa.comp_no = bb.comp_no                    						");
		query.append("         	 AND aa.equip_id = bb.equip_id                						");
		query.append("       	WHERE aa.wkor_id = a.wkor_id                    					");
		query.append("        	 AND aa.comp_no = a.comp_no   )					AS equipId  		");
		query.append("      , b.use_qty											AS useQty   		");
		query.append("      , b.tot_price										AS totPrice			");
		query.append("FROM    TAWORKORDER a INNER JOIN TAWOPARTS b              					");
		query.append("ON a.comp_no = b.comp_no                                  					");
		query.append("AND a.wkor_id = b.wkor_id                                 					");
		query.append("WHERE 1=1                                                 					");
		query.append("  AND a.wo_status = 'C'    													");
		query.append(this.getWhere(partRptPtUseHistDetailDTO, user));
		query.append(") x       																	");
		query.append("GROUP BY x.equipId                                        					");
		
		query.getOrderByQuery("x.equipId","x.equipId", partRptPtUseHistDetailDTO.getOrderBy(), partRptPtUseHistDetailDTO.getDirection());
		
		return getJdbcTemplate().queryForList(query.toString(partRptPtUseHistDetailDTO.getIsLoadMaxCount(), partRptPtUseHistDetailDTO.getFirstRow()));
	}

	private String getWhere(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user)
	{       
    	QuerySqlBuffer query = new QuerySqlBuffer(); 
		
		//ȸ��
		query.getStringEqualQuery("b.comp_no", user.getCompNo());
		// ����
        String fromDate = CommonUtil.dateToData(partRptPtUseHistDetailDTO.getStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(partRptPtUseHistDetailDTO.getEndDate()+"-01");
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  '"+fromDate+"'     ");
        }        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <=  CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }
		//�μ�
		query.getDeptLevelQuery("a.dept_id", partRptPtUseHistDetailDTO.getDeptId(), partRptPtUseHistDetailDTO.getDeptDesc(), user.getCompNo());
        
		//����
		query.getCodeLikeQuery("a.plant", "(SELECT x.description FROM TAPLANT x WHERE x.plant = a.plant AND x.comp_no='"+user.getCompNo()+"')", 
				partRptPtUseHistDetailDTO.getPlantId(), partRptPtUseHistDetailDTO.getPlantDesc());
        //����
		query.getCodeLikeQuery("b.part_id", "(SELECT x.full_desc FROM TAPARTS x WHERE x.part_id = b.part_id AND x.comp_no='"+user.getCompNo()+"')", 
				partRptPtUseHistDetailDTO.getPartId(), partRptPtUseHistDetailDTO.getPartDesc());
		
		return query.toString();
	}

	@Override
	public String findPartTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																		");
        query.append("		COUNT(1)																");
        query.append("FROM (    																	");
		query.append("	SELECT																		");
		query.append("		x.MONTH														AS	month	");
		query.append("	FROM (    																	");
		query.append("		SELECT																	");
		query.append("  		SUBSTRING(a.start_date,1,4) + '-' + SUBSTRING(a.start_date,5,2) MONTH	");
		query.append("		FROM TAWORKORDER a INNER JOIN TAWOPARTS b                               ");
		query.append("		ON a.comp_no = b.comp_no                                                ");
		query.append("		 AND a.wkor_id = b.wkor_id                                              ");
		query.append("		WHERE 1=1                                                               ");
		query.append("		 AND a.wo_status = 'C'    												");
		query.append(this.getWhere(partRptPtUseHistDetailDTO, user));
		query.append("	) x       																	");
		query.append("GROUP BY x.MONTH                                                            	");
		query.append(") xx     																		");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	@Override
	public String findEqTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT													");
		query.append("		COUNT(1)											");
		query.append("FROM (    												");
		query.append("SELECT													");
		query.append("		''									AS	seqNo		");
		query.append("FROM (    												");
		query.append("SELECT                                               		");
		query.append("      (SELECT bb.EQUIP_ID            						");
		query.append("       FROM TAWOEQUIP aa INNER JOIN TAEQUIPMENT bb    	");
		query.append("       ON aa.comp_no = bb.comp_no                    		");
		query.append("        AND aa.equip_id = bb.equip_id                		");
		query.append("       WHERE aa.wkor_id = a.wkor_id                   	");
		query.append("        AND aa.comp_no = a.comp_no   )	AS equipId		");
		query.append("FROM TAWORKORDER a INNER JOIN TAWOPARTS b					");
		query.append("ON a.comp_no = b.comp_no                                  ");
		query.append("AND a.wkor_id = b.wkor_id                                 ");
		query.append("WHERE 1=1                                                 ");
		query.append("  AND a.wo_status = 'C'    								");
		query.append(this.getWhere(partRptPtUseHistDetailDTO, user));
		query.append(") x       												");
		query.append("GROUP BY x.equipId                                        ");
		query.append(") xx       												");
		
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		
		return QuerySqlBuffer.listToString(resultList);
	
	}
    
}