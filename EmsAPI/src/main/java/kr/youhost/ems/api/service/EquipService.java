package kr.youhost.ems.api.service;

import java.util.List;

import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Equip;
import kr.youhost.ems.api.model.EquipType;
import kr.youhost.ems.api.model.ext.EquipChain;
import kr.youhost.ems.api.model.ext.Sector;
import kr.youhost.ems.api.model.ext.SectorPanel;
import kr.youhost.ems.api.model.ext.SectorPanelBase;

public interface EquipService {
	/**
	 * getEquipTypes
	 *  equipTypes list	 * @return
	 */
	List<EquipType> getEquipTypes() throws NotExistException;
	EquipType getEquipType(final int equipType) throws NotExistException;
	
	/**
	 * getRoot
	 *  최상위 ROOT equip 조회
	 * @return
	 */
	Equip getRoot() throws NotExistException;
	
	/**
	 * getEquips
	 *  equipType 에 해당하는 equip 목록
	 * @param equipType
	 * @return
	 */
	List<Equip> getEquips(final int equipType) throws NotExistException;
	/**
	 * getEquip
	 *  equipId 에 해당하는 equip
	 * @param equipId
	 * @return
	 */
	Equip getEquip(final int equipId) throws NotExistException;
	/**
	 * getChilds
	 *  equipId 에 속한 child equipid 목록
	 * @param equipId
	 * @return
	 */
	List<Equip> getChilds(final int equipId) throws NotExistException;
	/**
	 * getChilds
	 *  equipId 에 속한 childs 중 equipType 에 해당하는 child 목록
	 * @param equipId
	 * @param equip_type
	 * @return
	 */
	List<Equip> getChilds(final int equipId, final int equipType) throws NotExistException;
	
	List<Equip> getChildGroups(final int equipId) throws NotExistException;
	List<Equip> getChildPanels(final int equipId) throws NotExistException;
	List<Equip> getChildModules(final int equipId) throws NotExistException;
	List<Equip> getChildDevices(final int equipId) throws NotExistException;
	
	String getEquipPosition( final int equipId );
	String getEquipPosition( final int equipId, final boolean selfInclude ) ;
	
	Equip saveEquip(Equip equip);
	Equip saveEquip(Equip equip, boolean isNew);
	
	boolean delEquip(final int equipId) throws NotExistException;
	void delEquipGroup(final int equipId) throws NotExistException;
	
	List<Equip> getCircuitModules() throws NotExistException;

	/*
	 * ROOT 에서 equipId 까지 equip id list
	 */
	List<EquipChain> getEquipChains(final int equipId) throws Exception;
	
	List<Sector> sectors() throws Exception;
	List<SectorPanelBase> sectorPanelBases() throws Exception;
	
	List<Integer> findGoodEquips(final List<Integer> equipIds) ;
	
	List<SectorPanel> panelsBySector() throws Exception;
}
