package be.parrez.christoph.eurder.service;

import be.parrez.christoph.eurder.dto.OrderCreateDto;
import be.parrez.christoph.eurder.dto.OrderDto;
import be.parrez.christoph.eurder.dto.OrderReportDto;
import be.parrez.christoph.eurder.mapper.OrderMapper;
import be.parrez.christoph.eurder.model.*;
import be.parrez.christoph.eurder.repository.ItemRepository;
import be.parrez.christoph.eurder.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ItemRepository itemRepository,
                        OrderMapper orderMapper,
                        UserService userService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.addDummyData();
    }

    private void addDummyData() {
        ItemGroup itemGroup1 = new ItemGroup(itemRepository.getEntry("dummy-item-id-1"), 2);
        ItemGroup itemGroup2 = new ItemGroup(itemRepository.getEntry("dummy-item-id-2"), 1);
        ItemGroup itemGroup3 = new ItemGroup(itemRepository.getEntry("dummy-item-id-3"), 7);
        ItemGroup itemGroup4 = new ItemGroup(itemRepository.getEntry("dummy-item-id-4"), 3);
        ItemGroup itemGroup5 = new ItemGroup(itemRepository.getEntry("dummy-item-id-5"), 15);

        Order order1 = new Order("cd52f722-9530-user1-8fd1-184f12a75222", List.of(itemGroup1, itemGroup2));
        Order order2 = new Order("cd52f722-9530-user2-8fd1-184f12a75222", List.of(itemGroup2, itemGroup3));
        Order order3 = new Order("cd52f722-9530-user2-8fd1-184f12a75222", List.of(itemGroup3, itemGroup4, itemGroup5));

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }

    public OrderReportDto getOrderReport(String authorizedId) {
        logger.info("Getting order report with authorizedId " + authorizedId);
        User user = userService.assertUserPermissions(authorizedId, List.of(UserRole.ADMIN, UserRole.CUSTOMER), "You are not authorized to see orders.");
        return orderMapper.toReportDto(getAllOrders(user));
    }

    public List<OrderDto> getAllOrders(User user) {
        logger.info("Getting orders with authorizedId " + user.getId());
        return orderMapper.toDto(
                orderRepository.getEntries().stream()
                        .filter(order -> {
                            if (user.getUserRole().equals(UserRole.ADMIN)) {
                                return true;
                            }
                            return order.getCustomerId().equals(user.getId());
                        })
                        .toList()
        );
    }

    public OrderDto createOrder(String authorizedId, OrderCreateDto orderCreateDto) {
        userService.assertUserPermissions(authorizedId, List.of(UserRole.ADMIN, UserRole.CUSTOMER), "You are not authorized to create orders.");

        logger.info("Trying to order --> " + orderCreateDto.getItems().stream().map(item -> item.getItemId() + " x " + item.getAmount()).collect(Collectors.joining(", ")) + " as user with id " + authorizedId);

        Order newOrder = new Order(authorizedId, orderMapper.toEntity(orderCreateDto.getItems()));
        orderRepository.save(newOrder);

        return orderMapper.toDto(newOrder);
    }
}
