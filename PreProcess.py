import pandas as pd

data_file = r'data.csv'
data_df = pd.read_csv(data_file)

time_variable = ['created_at.y','created_at.x','driver_assigned_at','reached_shop_at'
                 ,'shipped_at','reached_customer_at','complete_at','linked_at']
for i in time_variable:
    print 'converting to datetime for : ', i
    data_df[i] = pd.to_datetime(data_df[i])

#########################variable creation

#''' creating the target variable kpi '''
data_df['kpt'] = data_df.apply(lambda x : x['shipped_at'] - x['linked_at'], axis  = 1)
print 'kpt created'

# Making driver_in_duration
data_df['driver_in_duration'] = data_df.apply(lambda x : x['reached_shop_at'] - x['driver_assigned_at'] , axis = 1)
print 'driver_in_duration created'

# Making driver_rest_wait_time
data_df['driver_rest_wait_time'] = data_df.apply(lambda x : x['shipped_at'] - x['reached_shop_at'] , axis = 1)
print 'driver_rest_wait_time created'

# Making driver_out_duration
data_df['driver_out_duration'] = data_df.apply(lambda x : x['reached_customer_at'] - x['shipped_at'] , axis = 1)
print 'driver_out_duration created'

# Making driver_cust_wait_time
data_df['driver_cust_wait_time'] = data_df.apply(lambda x : x['complete_at'] - x['reached_customer_at'] , axis = 1)
print 'driver_cust_wait_time created'

# Making driver_assign_duration
data_df['driver_assign_duration'] = data_df.apply(lambda x : x['driver_assigned_at'] - x['linked_at'] , axis = 1)
print 'driver_assign_duration created'

# Making order_accept_time
data_df['order_accept_time'] = data_df.apply(lambda x : x['linked_at'] - x['linked_at'] , axis = 1)
print 'order_accept_time created'

#############analysing the new variables
time_new_var = ['kpt', 'driver_in_duration', 'driver_rest_wait_time','driver_out_duration',
            'driver_cust_wait_time','driver_assign_duration', 'order_accept_time']

for i in time_new_var:
    print i, sum(data_df[i].isnull())

data_df = data_df[(data_df['kpt'] > pd.Timedelta(0)) & (data_df['kpt']< pd.Timedelta(hours = 5))]
#############dropping 1263 rows for null values and invalid entires
data_df = data_df[(data_df['driver_out_duration'] < pd.Timedelta(hours = 5)) & (data_df['driver_out_duration'] >= pd.Timedelta(0))]

data_df.to_csv('data_1.csv', index = False)

data_df = data_df[(data_df['driver_cust_wait_time'] >= pd.Timedelta(0)) &(data_df['driver_cust_wait_time'] < pd.Timedelta(hours=5))]