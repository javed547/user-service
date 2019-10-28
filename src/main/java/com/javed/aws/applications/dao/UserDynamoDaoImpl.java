package com.javed.aws.applications.dao;

import com.javed.aws.applications.model.Login;
import com.javed.aws.applications.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDynamoDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDynamoDaoImpl.class.getName());

    @Override
    public void register(User user) {
        {
            DynamoDbClient dynamoDbClient = DynamoDbClient.create();
            logger.debug("Successfully created DynamoDB client and going to create user with username : {}", user.getUsername());

            HashMap<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();
            prepareUserInsert(itemValues, user);

            PutItemRequest putItemRequest = PutItemRequest
                    .builder()
                    .tableName("users")
                    .item(itemValues)
                    .build();

            try {
                dynamoDbClient.putItem(putItemRequest);
                logger.debug("Successfully inserted user data for username : {}", user.getUsername());
            } catch (DynamoDbException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            } finally {
                dynamoDbClient.close();
            }
        }

    }

    private HashMap<String, AttributeValue> prepareUserInsert(HashMap<String, AttributeValue> itemValues, User user) {
        itemValues.put("username", AttributeValue.builder().s(user.getUsername()).build());
        itemValues.put("password", AttributeValue.builder().s(user.getPassword()).build());
        itemValues.put("firstname", AttributeValue.builder().s(user.getFirstname()).build());

        itemValues.put("lastname", AttributeValue.builder().s(user.getLastname()).build());
        itemValues.put("email", AttributeValue.builder().s(user.getEmail()).build());
        itemValues.put("address", AttributeValue.builder().s(user.getAddress()).build());

        itemValues.put("phone", AttributeValue.builder().s(String.valueOf(user.getPhone())).build());
        return itemValues;
    }

    @Override
    public User validateUser(Login login) {
        DynamoDbClient dynamoDbClient = DynamoDbClient.create();
        logger.debug("Successfully created DynamoDB client and going to make query call with username as {}", login.getUsername());
        User user = null;

        Map<String, String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#username", "username");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":usernameValue", AttributeValue.builder().s(login.getUsername()).build());

        QueryRequest queryRequest = QueryRequest
                .builder()
                .tableName("users")
                .keyConditionExpression("#username = :usernameValue")
                .expressionAttributeNames(expressionAttributesNames)
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        try {
            QueryResponse queryResponse = dynamoDbClient.query(queryRequest);
            logger.debug("Successfully queried DynamoDB and pulled user details for username : {}", login.getUsername());
            user = UserMapper(queryResponse.items());
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } finally {
            dynamoDbClient.close();
        }

        return user;
    }

    private User UserMapper(List<Map<String, AttributeValue>> items) {
        User users = new User();

        users.setUsername(items.get(0).get("username").s());
        users.setPassword(items.get(0).get("password").s());
        users.setFirstname(items.get(0).get("firstname").s());

        users.setLastname(items.get(0).get("lastname").s());
        users.setEmail(items.get(0).get("email").s());
        users.setAddress(items.get(0).get("address").s());
        //users.setPhone(Long.parseLong(items.get(0).get("phone").s()));

        logger.debug("Successfully casted DynamoDB response to user object");
        return users;
    }
}
