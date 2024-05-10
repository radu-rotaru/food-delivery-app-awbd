package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.CourierDTO;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.repository.CourierRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourierServiceImpl implements CourierService{

    CourierRepository courierRepository;
    ModelMapper modelMapper;

    public CourierServiceImpl(CourierRepository courierRepository, ModelMapper modelMapper) {
        this.courierRepository = courierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourierDTO> findAll(){
        List<Courier> couriers = new LinkedList<>();
        courierRepository.findAll(Sort.by("name")).iterator().forEachRemaining(couriers::add);

        return  couriers.stream().map(courier -> modelMapper.map(courier, CourierDTO.class)).collect(Collectors.toList());

    }

    @Override
    public Optional<Courier> getCourier(Long courierId) {
        return courierRepository.findById(courierId);
    }

    @Override
    public Boolean doesExist(Long courierId) {
        return courierRepository.findById(courierId).isPresent();
    }

    @Override
    public CourierDTO save(CourierDTO courier) {
        Courier savedCourier = courierRepository.save(modelMapper.map(courier, Courier.class));
        return modelMapper.map(savedCourier, CourierDTO.class);
    }

    @Override
    public void deleteById(Long id){courierRepository.deleteById(id);}

}
