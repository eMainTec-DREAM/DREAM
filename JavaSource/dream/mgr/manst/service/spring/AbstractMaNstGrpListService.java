package dream.mgr.manst.service.spring;

import java.util.List;

import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.service.MaNstGrpListService;

public abstract class AbstractMaNstGrpListService implements MaNstGrpListService{

	protected MaNstGrpListService maNstGrpListService;
	
	public AbstractMaNstGrpListService(MaNstGrpListService maNstGrpListService) {
		this.maNstGrpListService = maNstGrpListService;
	}
	
	public abstract List findNstGrpList(MaNstGrpCommonDTO maNstGrpCommonDTO);
	public abstract int deleteNstGrp(String[] deleteRows, MaNstGrpCommonDTO maNstGrpCommonDTO) throws Exception;
}
