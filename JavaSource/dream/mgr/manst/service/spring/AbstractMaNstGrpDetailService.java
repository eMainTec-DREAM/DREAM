package dream.mgr.manst.service.spring;

import common.bean.User;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;
import dream.mgr.manst.service.MaNstGrpDetailService;

public abstract class AbstractMaNstGrpDetailService implements MaNstGrpDetailService{

	protected MaNstGrpDetailService maNstGrpDetailService;
	
	public AbstractMaNstGrpDetailService(MaNstGrpDetailService maNstGrpDetailService) {
		this.maNstGrpDetailService = maNstGrpDetailService;
	}
	
	public abstract MaNstGrpDetailDTO findDetail(MaNstGrpCommonDTO maNstGrpCommonDTO, User loginUser)throws Exception;
    public abstract int insertDetail(MaNstGrpDetailDTO maNstGrpDetailDTO) throws Exception;
    public abstract int updateDetail(MaNstGrpDetailDTO maNstGrpDetailDTO) throws Exception;
    
}
