package dream.part.rpt.ptusehist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.part.rpt.ptusehist.dao.PartRptPtUseHistListDAO;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistCommonDTO;

/**
 * ��ǰ ��� �м� - List DAO implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="partRptPtUseHistListDAOTarget"
 * @spring.txbn id="partRptPtUseHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartRptPtUseHistListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartRptPtUseHistListDAO
{
	public List findPtUseList(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																		");
        query.append("		''														AS	seqNo		");
        query.append("      ,c.part_no                    	                        AS	partNo		");
        query.append("      ,c.description         				                    AS	partDesc	");
        query.append("      ,c.pt_size         				                        AS	ptSize     	");
        query.append("      ,c.model         				                        AS	model      	");
        query.append("      ,COUNT(use_qty)          								AS	useCnt    	");
        query.append("      ,SUM(use_qty) 						    				AS	useQty 		");
        query.append("      ,NVL(SUM(b.tot_price),0)                               	AS	totPrice 	");
        query.append("      ,b.part_id                          					AS	partId		");
        query.append("FROM TAWORKORDER a INNER JOIN TAWOPARTS b              						");
        query.append("ON a.comp_no = b.comp_no                                  					");
        query.append(" AND a.wkor_id = b.wkor_id                                 					");
        query.append("INNER JOIN TAPARTS c              						                    ");
        query.append("ON b.comp_no = c.comp_no                                  					");
        query.append(" AND b.part_id = c.part_id                                 					");
        query.append("WHERE 1=1																		");
        query.append("  AND a.wo_status = 'C'                                   					");
        query.append(this.getWhere(partRptPtUseHistCommonDTO, user));
        query.append("GROUP BY b.part_id, c.part_no, c.description, c.pt_size, c.model				");

        query.getOrderByQuery("partNo", partRptPtUseHistCommonDTO.getOrderBy(), partRptPtUseHistCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partRptPtUseHistCommonDTO.getIsLoadMaxCount(), partRptPtUseHistCommonDTO.getFirstRow()));
    } 

	private String getWhere(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        //ȸ��
        query.getStringEqualQuery("b.comp_no", user.getCompNo());
        //����
        String fromDate = CommonUtil.dateToData(partRptPtUseHistCommonDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(partRptPtUseHistCommonDTO.getFilterEndDate());
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.start_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYY-MM'),'YYYYMMDD')     ");
        }
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.end_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYY-MM'),1),'YYYYMMDD')        ");
        } 
        //�μ�
        query.getDeptLevelQuery("a.dept_id", partRptPtUseHistCommonDTO.getFilterDeptId(), partRptPtUseHistCommonDTO.getFilterDeptDesc(), user.getCompNo());
        //����
        query.getCodeLikeQuery("a.plant", "(SELECT x.description FROM TAPLANT x WHERE x.plant = a.plant AND x.comp_no='"+user.getCompNo()+"')", 
        		partRptPtUseHistCommonDTO.getFilterPlantId(), partRptPtUseHistCommonDTO.getFilterPlantDesc());
        //����
    	query.append("AND b.part_id IS NOT NULL		");
        query.getCodeLikeQuery("b.part_id", "(SELECT x.full_desc FROM TAPARTS x WHERE x.part_id = b.part_id AND x.comp_no='"+user.getCompNo()+"')", 
        		partRptPtUseHistCommonDTO.getFilterPartId(), partRptPtUseHistCommonDTO.getFilterPartDesc());

    	return query.toString();
    }

    public String findTotalCount(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                               	");
        query.append("       COUNT(1)                                      	");
        query.append("FROM                                                 	");
        query.append("(                                                    	");
        query.append("	SELECT                                             	");
        query.append("      b.part_id							AS	parId	");
        query.append("	FROM TAWORKORDER a INNER JOIN TAWOPARTS b           ");
        query.append("	ON a.comp_no = b.comp_no                            ");
        query.append("	 AND a.wkor_id = b.wkor_id                          ");
        query.append("  INNER JOIN TAPARTS c                                ");
        query.append("  ON b.comp_no = c.comp_no                            ");
        query.append("   AND b.part_id = c.part_id                          ");
        query.append("	WHERE 1=1											");
        query.append("	  AND a.wo_status = 'C'                             ");
        query.append(this.getWhere(partRptPtUseHistCommonDTO, user));
        query.append("	GROUP BY b.part_id, c.part_no, c.description, c.pt_size, c.model");
        query.append(")                                                    	");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}