import pandas as pd


data_file = r'data.csv'
data_df = pd.read_csv(data_file)
print data_df.shape  # (484192, 15)
print data_df.columns
'''columns_names = [u'id', u'pickup_user_id', u'total_amount', u'pickup_user_address_id',
       u'created_at.x', u'ki', u'cost_for_two', u'created_at.y',
       u'driver_assigned_at', u'reached_shop_at', u'shipped_at',
       u'reached_customer_at', u'complete_at', u'linked_at', u'item_name']
2 columns are extra. the columns : 'ki', 'created_at.x'
'''
sample_df = data_df.head(100)
sample_df.to_csv('sample_data.csv', index = False)
data_df.describe()
''' gives stats about the numerical variables, only meaningful are from pickup_user_address_id, cost_from_two'''
print data_df.dtypes

obj_var = data_df.dtypes.index[data_df.dtypes == 'object']
'''[u'created_at.x', u'ki', u'created_at.y', u'driver_assigned_at', u'reached_shop_at', u'shipped_at',
    u'reached_customer_at', u'complete_at', u'linked_at', u'item_name']'''

non_obj_var = data_df.dtypes.index[data_df.dtypes != 'object']
'''[u'id', u'pickup_user_id', u'total_amount', u'pickup_user_address_id', u'cost_for_two']'''

'''analysis for variable : id'''
val_c_id = data_df['id'].value_counts()
print len(val_c_id)# 195319
print sum(val_c_id)   # the sum is eaqual to the total rows in the data, so no missing value
val_c_id.value_counts().plot.bar() # analysing the duplicacies in the the id
#occurance of id varies from 1 to 60.

'''analysis for variable : pickup_user_id'''
val_c_user_id = data_df['pickup_user_id'].value_counts()
print len(val_c_user_id)# 2847
print sum(val_c_user_id)   # the sum is eaqual to the total rows in the data, so no missing value

'''analysis for variable : total_amount'''
print data_df['total_amount'].mean() # 578.257935694931
print  data_df['total_amount'].std() # 615.4290590777657
print  data_df['total_amount'].min() # 30
print  data_df['total_amount'].skew() # 8.935612965123536 highly skewed
data_df.sort_values(['total_amount'])['total_amount'].reset_index(drop = True).plot()
'''
there is a unique behaviour in the variable: 
there are high occurance of the heigh amount : 
29070.48 : 13
17914.76 : 18
 '''

'''analysis for variable : pickup_user_address_id'''
val_c_user_addr_id = data_df['pickup_user_address_id'].value_counts()
print len(val_c_user_addr_id)# 3194
print sum(val_c_user_addr_id)   # the sum is eaqual to the total rows in the data, so no missing value

'''analysis for variable : created_at.y'''
sum(data_df['created_at.y'].isnull())
print data_df['created_at.y'].head() #timestamp
# data_df['created_at.y'] = pd.to_datetime(data_df['created_at.y'])

'''analysis for variable : created_at.x'''
print data_df['created_at.x'].head() # null values
sum(data_df['created_at.x'].isnull()) # number of null values 372477
data_df['created_at.x'][~data_df['created_at.x'].isnull()].head()
# data_df['created_at.x'] = pd.to_datetime(data_df['created_at.x'])
# the null values will be NaT not a time

'''analysis for variable : ki'''
print data_df['ki'].head() # null values
sum(data_df['ki'].isnull()) # number of null values 372477
data_df['ki'][~data_df['ki'].isnull()].head()
data_df['ki'].value_counts(dropna=False)

'''analysis for variable : cost_for_two'''
print data_df['cost_for_two'].head() # null values
sum(data_df['cost_for_two'].isnull()) # number of null values 372477
data_df['cost_for_two'][~data_df['cost_for_two'].isnull()].head()
data_df['cost_for_two'].value_counts(dropna=False)

'''analysis for variable : driver_assigned_at'''
print data_df['driver_assigned_at'].head() # null values
sum(data_df['driver_assigned_at'].isnull()) # number of null values 281
# data_df['driver_assigned_at'] = pd.to_datetime(data_df['driver_assigned_at'])

'''analysis for variable : reached_shop_at'''
print data_df['reached_shop_at'].head() # null values
sum(data_df['reached_shop_at'].isnull()) # number of null values 0
# data_df['reached_shop_at'] = pd.to_datetime(data_df['reached_shop_at'])

'''analysis for variable : shipped_at'''
print data_df['shipped_at'].head() # null values
sum(data_df['shipped_at'].isnull()) # number of null values 290
# data_df['shipped_at'] = pd.to_datetime(data_df['shipped_at'])

'''analysis for variable : reached_customer_at'''
print data_df['reached_customer_at'].head() # null values
sum(data_df['reached_customer_at'].isnull()) # number of null values 1391
# data_df['reached_customer_at'] = pd.to_datetime(data_df['reached_customer_at'])

'''analysis for variable : complete_at'''
print data_df['complete_at'].head() # null values
sum(data_df['complete_at'].isnull()) # number of null values 2013
# data_df['complete_at'] = pd.to_datetime(data_df['complete_at'])

'''analysis for variable : linked_at'''
print data_df['linked_at'].head() # null values
sum(data_df['linked_at'].isnull()) # number of null values 0
# data_df['linked_at'] = pd.to_datetime(data_df['linked_at'])

'''analysis for variable : item_name'''
print data_df['item_name'].head() # null values
sum(data_df['item_name'].isnull()) # number of null values 0
val_c_item_nm = data_df['item_name'].value_counts()
print len(val_c_item_nm)# 42554


data_df['kpt'].min() # should be > 0
print data_df[data_df['kpt']< pd.Timedelta(0)] # 2 values
# need to drop these 2 entries
print data_df[data_df['kpt']> pd.Timedelta(days = 1)].shape # 16 values
print data_df[data_df['kpt']> pd.Timedelta(hours = 5)].shape # 46 values
print data_df[data_df['kpt']> pd.Timedelta(hours = 2)].shape # 468 values
print data_df[data_df['kpt']> pd.Timedelta(hours = 1)].shape # 12027 values
# need to drop the 46 entries
print 'number of null values : ', sum(data_df['kpt'].isnull()) # 290
# need to drop the 290 entries

